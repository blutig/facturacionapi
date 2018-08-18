/**
 * 
 */
package co.com.sistemafacturacion.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import co.com.sistemafacturacion.api.infraestructura.KafkaSender;
import co.com.sistemafacturacion.api.modelo.Factura;

@RestController
@RequestMapping(value = "/sistemafacturacion/factura")
public class FacturaController {

	@Value("${kafka.name-topic-sap}")
	private String nameTopicSap;

	@Autowired
	private KafkaSender sender;

	@RequestMapping(method = RequestMethod.POST, value = "/crear")
	public @ResponseBody ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {

		Gson gson = new Gson();
		String jsonFactura = gson.toJson(factura);

		sender.enviarMensaje(nameTopicSap, jsonFactura);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setExpires(2000);
		httpHeaders.set("Content-Type", "application/json");

		return new ResponseEntity<Factura>(factura, httpHeaders, HttpStatus.CREATED);
	}
}
