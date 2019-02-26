package ds.pruebas;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.eclipse.jetty.http.HttpStatus;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class ViewUtil {

    // Renders a template given a model and a request
    // The request is needed to check the user session for language settings
    // and to see if the user is logged in
    public static String render(Request request, Map<String, Object> model, String templatePath) {
    	System.out.println("Render");
    	
        model.put("msg", new MessageBundle("sp"));
        model.put("currentUser", RequestUtil.getSessionCurrentUser(request));
        model.put("WebPath", PathUtil.Web.class); // Access application URLs from templates
        //System.out.println(model);
        return strictVelocityEngine().render(new ModelAndView(model, templatePath));
    }

    public static String renderContent(String htmlFile) {	   
		try { 
	        Path path = Paths.get(htmlFile);
	        byte[] array = Files.readAllBytes(path);
	        String result = new String(array, Charset.defaultCharset());
	        return result;
	    } catch (IllegalArgumentException| OutOfMemoryError | SecurityException | IOException e) {
	    // Add your own exception handlers here.
	    }
	    return null;
	}
    public static Route notAcceptable = (Request request, Response response) -> {
        response.status(HttpStatus.NOT_ACCEPTABLE_406);
        return new MessageBundle(RequestUtil.getSessionLocale(request)).get("ERROR_406_NOT_ACCEPTABLE");
    };

    public static Route notFound = (Request request, Response response) -> {
        response.status(HttpStatus.NOT_FOUND_404);
        return render(request, new HashMap<>(), PathUtil.Template.NOT_FOUND);
    };

    private static VelocityTemplateEngine strictVelocityEngine() {
        VelocityEngine configuredEngine = new VelocityEngine();
        configuredEngine.setProperty("runtime.references.strict", true);
        configuredEngine.setProperty("resource.loader", "class");
        configuredEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return new VelocityTemplateEngine(configuredEngine);
    }
}
