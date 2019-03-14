package ds.central;

public class Mensaje {
	
	private String origen;
	private String destino;
	private String mensaje;
	
	
	
	public Mensaje() {
		super();
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	@Override
	public String toString() {
		return "Mensaje [origen=" + origen + ", destino=" + destino + ", mensaje=" + mensaje + "]";
	}
	
	
}
