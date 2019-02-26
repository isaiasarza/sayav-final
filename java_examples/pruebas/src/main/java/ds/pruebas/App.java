package ds.pruebas;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Hello world!
 *
 */
public class App {
	static EmpleadoDao empleadoDao = new EmpleadoDao();

	public static void main(String[] args) {
		// System.out.println( "Hello World!" );

		get("/login", App.serveLoginPage);

		get("/hello", (req, res) -> "Hello World");

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
