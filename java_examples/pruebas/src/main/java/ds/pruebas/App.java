package ds.pruebas;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import ds.central.Central;
import ds.central.CentralRest;
import ds.central.Mensaje;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Hello world!
 *
 */
public class App {
	static EmpleadoDao empleadoDao = new EmpleadoDao();
	static Central central = CentralRest.getInstance();
	static ObjectMapper mapper = new ObjectMapper(); 

	public static void main(String[] args) {
		port(central.getConfigurator().getPort());
		
		central.start();
		
		System.out.println(central.getConfigurator());
		
		post("/", (req, res) -> {
			Mensaje mensaje = mapper.readValue(req.bodyAsBytes(), Mensaje.class);
			central.recibirMensaje(mensaje);
			return "ok";
		});

		get("/enviar", (req, res) -> {
			Mensaje mensaje = mapper.readValue(req.bodyAsBytes(), Mensaje.class);
			System.out.println(central.enviarMensaje(mensaje));
			return "ok";
		});

		
		get("/login", App.serveLoginPage);
		
		post("/", (request, response) -> {
		    // Create something
			return null;
		});

		get("/hello", (req, res) -> "Hello World");
		
		get("/enviar",(req,res) -> {
			Mensaje mensaje = mapper.readValue(req.bodyAsBytes(), Mensaje.class);			
			return central.enviarMensaje(mensaje);
		});
		
		get("/vincular",(req,res)->{
			return central.vincular("","alarma/on");
		});

		
		get("/vincular/:prefix",(req,res)->{
			return central.vincular("", "/" + req.params(":prefix") + "/alarma/on");
		});

		get("/empleado", (request, response) -> {
			Empleado empleado;
			try {
				empleado = empleadoDao.getEmpleado();
				return empleado;
			} catch (Exception e) {
				return e;
			}

		});

		get("/guardar/empleado", (request, response) -> {
			Empleado empleado = new Empleado();
			empleado.setNombre("Paco");
			empleado.setApellido("De Lucía");
			empleado.setDepartamento("Flamenco");
			empleado.setSueldo(65999);

			try {
				empleadoDao.saveEmpleado(empleado);
			} catch (Exception e) {
				return "Error " + e;
			}

			return "OK";
		});
	}

	public static Route serveLoginPage = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();

		try {
			String res = ViewUtil.render(request, model, "resources" + PathUtil.Template.LOGIN);
			return res;
		} catch (Exception e) {
			System.out.println(e);
			return e;
		}

	};
}
