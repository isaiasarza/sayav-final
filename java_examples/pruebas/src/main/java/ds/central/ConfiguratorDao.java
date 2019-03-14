package ds.central;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfiguratorDao {
	private ObjectMapper mapper; 
	private URL url;
	private File file;
	private InputStream input;
	public ConfiguratorDao() {
		super();
		mapper = new ObjectMapper();
		url = this.getClass().getResource("/resources/conf.json");
		try {
			input = url.openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Configurator get() throws JsonParseException, JsonMappingException, IOException {
		
		return mapper.readValue(input,Configurator.class);
	}
	
	public void save(Configurator configurator) throws JsonGenerationException, JsonMappingException, IOException {
		mapper.writeValue(file,configurator);
	}

}
