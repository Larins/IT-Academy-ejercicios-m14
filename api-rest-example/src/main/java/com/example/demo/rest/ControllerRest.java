package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ProductosDAO;
import com.example.demo.models.Producto;

@RestController //Esta clase va a ser un servicio REST
@RequestMapping("/productos") //En esta URL se van a exponer los servicios de esta clase
public class ControllerRest {

	@Autowired //Inyección de dependencias
	private ProductosDAO productosDAO;

	@GetMapping
	public ResponseEntity<List<Producto>> getProducto() {
		List<Producto> productos = productosDAO.findAll();
		return ResponseEntity.ok(productos);
		//Producto producto = new Producto();
		//producto.setId(1);
		//producto.setName("Producto 1");
		//return ResponseEntity.ok(producto);
	}

	//NO ME FUNCIONA EL METODO POST :( PREGUNTAR EDUARD

	@PostMapping //productos (POST)
	public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
		Producto newProduct = productosDAO.save(producto);
		return ResponseEntity.ok(newProduct);
	}

	@DeleteMapping(value="{productId}")
	public ResponseEntity<Void> deleteProducto(@PathVariable("productId") Long productId) {
		productosDAO.deleteById(productId);
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value="{productId}")
	public ResponseEntity<Producto> getProductoById(@PathVariable("productId") Long productId) {
		Optional<Producto> optionalProducto = productosDAO.findById(productId);
		if (optionalProducto.isPresent()) {
			return ResponseEntity.ok(optionalProducto.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	//NO ME FUNCIONA EL METODO PUT :( PREGUNTAR EDUARD

	@PutMapping
	public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto) {
		Optional <Producto> optionalProducto = productosDAO.findById(producto.getId());
		if (optionalProducto.isPresent()) {
			Producto updateProducto = optionalProducto.get();
			updateProducto.setName(producto.getName());
			productosDAO.save(updateProducto);
			return ResponseEntity.ok(updateProducto);
		} else {
			return ResponseEntity.notFound().build();
		}
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
		//@RequestMapping("/productos") + @GetMapping + @RequestMapping(value="{productId}") apunta a localhost:8080/productos/1 (por ejemplo)
	//Todo lo demás (lo de dentro del public String blabla) queda igual


}
