/**
 * 
 */
package co.com.sistemafacturacion.api.infraestructura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author drache
 *
 */
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void enviarMensaje(String topic, String payload) {
		kafkaTemplate.send(topic, payload);
	}
}
