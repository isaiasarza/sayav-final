package ds.pruebas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmpleadoDao {
	File file;
	ObjectMapper mapper; // create once, reuse
	URLClassLoader urlLoader=(URLClassLoader)this.getClass().getClassLoader();
	URL url;
	public EmpleadoDao() {
		super();
		url = this.getClass().getResource("/data.json");
		//file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator +"data.json");
		file = new File(url.getPath());
		mapper = new ObjectMapper();
	}

	public Empleado getEmpleado() throws JsonParseException, JsonMappingException, IOException {
        //System.out.println(workingDir + File.separator + "src" + File.separator +"data.json");
        System.out.println(urlLoader.getResource("src" + File.separator + "data.json"));
        System.out.println(this.getClass().getResource("/resources/data.json"));
        System.out.println(this.getClass().getResource("/data.json"));

		return mapper.readValue(file, Empleado.class);
	}

	public void saveEmpleado(Empleado empleado) throws JsonGenerationException, JsonMappingException, IOException {
		mapper.writeValue(file, empleado);
	}

}
