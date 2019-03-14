package ds.central;

public class Configurator {
	
	private String broker;
	private String clientId;
	private int port;
	
	public Configurator() {
		super();
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "Configurator [broker=" + broker + ", clientId=" + clientId + ", port=" + port + "]";
	}
	
	
	
	
	

}
