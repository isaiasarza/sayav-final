package ds.central;

public interface Central {
	
	public String enviarMensaje(Mensaje mensaje);
	
	public void recibirMensaje(Mensaje mensaje);
		
	public boolean vincular(String central, String topico);
	
	public boolean desvincular(String central, String topico);

	public void start();

	public Configurator getConfigurator();
	
	

}
