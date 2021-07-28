package com.example.demo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Producto;

@RestController //Esta clase va a ser un servicio REST
@RequestMapping("/productos") //En esta URL se van a exponer los servicios de esta clase
public class ControllerRest {

	@GetMapping
	public ResponseEntity<Producto> getProducto() {
		Producto producto = new Producto();
		producto.setId(1);
		producto.setName("Producto 1");
		return ResponseEntity.ok(producto);
	}

	//@GetMapping("hello") //Servicio disponible mediante GET
	//RequestMapping(value="hello", method=RequestMethod.GET) //URL en qué está el servicio
	public String hello() {
		return "hello world";
	}

	//NOTA SOBRE LAS URL
	//Podemos especificar la URL que deseemos
	//La vamos construyendo mediante @RequestMapping y @GetMapping
	//Ejemplos:
		//@RequestMapping("/") + @GetMapping("") apunta a localhost:8080/
		//@RequestMapping("/") + @GetMapping("hello") apunta a localhost:8080/hello
		//@RequestMapping("/productos") + @GetMapping("cualquiercosa") apunta a localhost:8080/productos/cualquiercosa
	//Todo lo demás (lo de dentro del public String blabla) queda igual


}
