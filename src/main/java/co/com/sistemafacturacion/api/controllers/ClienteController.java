/**
 * 
 */
package co.com.sistemafacturacion.api.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.sistemafacturacion.api.modelo.Cliente;

@RestController
@RequestMapping(value = "/sistemafacturacion/cliente")
public class ClienteController {

	@RequestMapping(method = RequestMethod.POST, value = "/crear")
	public @ResponseBody ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setExpires(2000);
		httpHeaders.set("Content-Type", "application/json");

		return new ResponseEntity<Cliente>(cliente, httpHeaders, HttpStatus.CREATED);
	}
}
