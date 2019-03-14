package ds.central;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CentralRest implements Central {

	private Configurator conf;
	private ConfiguratorDao confDao;
	private static Central central;

	private CentralRest() {
		super();
		Unirest.setObjectMapper(new ObjectMapper() {
			private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return jacksonObjectMapper.readValue(value, valueType);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			public String writeValue(Object value) {
				try {
					return jacksonObjectMapper.writeValueAsString(value);
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			}
		});
		confDao = new ConfiguratorDao();
		try {
			conf = confDao.get();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Central getInstance() {
		if (central == null) {
			central = new CentralRest();
		}
		return central;
	}

	@Override
	public String enviarMensaje(Mensaje mensaje) {

		try {
			HttpResponse<String> jsonResponse = (Unirest.post(mensaje.getDestino())
					.header("accept", "application/json")
					.header("Content-Type", "application/json")
					.body(mensaje)
					.asString());
			return jsonResponse.getBody().toString();
		} catch (UnirestException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public boolean vincular(String central, String grupo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean desvincular(String central, String grupo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public Configurator getConfigurator() {
		// TODO Auto-generated method stub
		return conf;
	}

	@Override
	public void recibirMensaje(Mensaje mensaje) {
		System.out.println(mensaje);
	}

}
