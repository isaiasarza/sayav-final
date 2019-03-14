package ds.central;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class CentralImpl implements Central, MqttCallback {

	private Configurator conf;
	private ConfiguratorDao confDao;
	private IMqttAsyncClient client;
	private MqttConnectOptions options;

	public CentralImpl() {
		super();
		confDao = new ConfiguratorDao();
		try {
			conf = confDao.get();
			client = new MqttAsyncClient(conf.getBroker(), conf.getClientId());
			options = new MqttConnectOptions();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		try {
			client.connect(options);
			client.setCallback(this);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public void end() {
		try {
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String enviarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		MqttMessage msg = new MqttMessage();
		IMqttDeliveryToken result;
		msg.setQos(0);
		msg.setPayload("asdfsadf".getBytes());
		try {
			result = client.publish(mensaje.getDestino(), msg);
			return result.toString();
		} catch (MqttException e) {
			return e.getMessage();
		}
	}

	@Override
	public boolean vincular(String central, String topico) {
		try {
			client.subscribe(topico, 0);
			return true;
		} catch (MqttException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean desvincular(String central, String topico) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage arg1) throws Exception {

		System.out.println("Llego un nuevo mensaje");

		System.out.println("Topico: " + topic);

		System.out.println("Mensaje: " + new String(arg1.getPayload()));
	}

	@Override
	public Configurator getConfigurator() {
		return conf;
	}

	@Override
	public void recibirMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		
	}

}
