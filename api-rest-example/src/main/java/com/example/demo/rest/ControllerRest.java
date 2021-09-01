package com.example.demo.rest;

import java.util.ArrayList;
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
import com.example.demo.dao.TiendasDAO;
import com.example.demo.models.Producto;
import com.example.demo.models.Tienda;


@RestController //Esta clase va a ser un servicio REST
@RequestMapping("/whitecollar") //En esta URL se van a exponer los servicios de esta clase
//URL: http://localhost:8080/whitecollar/ (ESTA URL NO DA INFORMACIÓN PERO SIRVE DE BASE PARA LAS CONSULTAS)
public class ControllerRest {

	@Autowired //Inyección de dependencias
	private TiendasDAO tiendasDAO;

	//METODOS CRUD PARA CONSULTAR LAS TIENDAS
	
	@GetMapping("shops") //tiendas GET todas
	//URL: http://localhost:8080/whitecollar/shops/
	public ResponseEntity<List<Tienda>> getTiendas() {
		List<Tienda> tiendas = tiendasDAO.findAll();
		return ResponseEntity.ok(tiendas);
	}
	
	@RequestMapping(value="shops/{tiendaId}")  //tiendas GET por ID
	//URL: http://localhost:8080/whitecollar/shops/1
	public ResponseEntity<Tienda> getTiendaById(@PathVariable("tiendaId") Long tiendaId) {
		Optional<Tienda> optionalTienda = tiendasDAO.findById(tiendaId);
		if (optionalTienda.isPresent()) {
			return ResponseEntity.ok(optionalTienda.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("shops") //tiendas POST (insertar nueva tienda)
	public ResponseEntity<Tienda> crearTienda(@RequestBody Tienda tienda) {
		Tienda newTienda = tiendasDAO.save(tienda);
		return ResponseEntity.ok(newTienda);
	}
	
	@PutMapping("shops") //tiendas PUT (modificar tienda existente)
	public ResponseEntity<Tienda> updateTienda(@RequestBody Tienda tienda) {
		Optional <Tienda> optionalTienda = tiendasDAO.findById(tienda.getId());
		if (optionalTienda.isPresent()) {
			Tienda updateTienda = optionalTienda.get();
			updateTienda.setName(tienda.getName());
			tiendasDAO.save(updateTienda);
			return ResponseEntity.ok(updateTienda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(value="shops/{tiendaId}") //tiendas DELETE (eliminar tienda existente)
	public ResponseEntity<Void> deleteTiendas(@PathVariable("tiendaId") Long tiendaId) {
		tiendasDAO.deleteById(tiendaId);
		return ResponseEntity.ok(null);
	}
	
	//MÉTODOS CRUD PARA CONSULTAR LOS PRODUCTOS (DE UNA TIENDA CONCRETA)
	//Lo que pide el ejercicio: extraer, por ejemplo, listados de productos por tienda
	
	@Autowired //Inyección de dependencias
	private ProductosDAO productosDAO;
	
	@GetMapping(value="products/shop/{tiendaId}") //productos GET todos en una tienda
	//URL: http://localhost:8080/whitecollar/products/shop/1
	public ResponseEntity<List<Producto>> getProductoTienda(@PathVariable("tiendaId") Long shopid) {
		
		List<Producto> productosTienda = new ArrayList<Producto>(); 
		//primera opcion: bucle for each
		/*
		List<Producto> productos = productosDAO.findAll();
		for (producto p: productos ) {
			if el producto es de la tienda
			productosTienda.add(p);
		}*/
		//segunda opcion: usando las funciones jpa
		productosTienda = productosDAO.findAllByshopid(shopid);
		return ResponseEntity.ok(productosTienda);
		

	}
	
	@RequestMapping(value="products/shop/{tiendaId}/product/{productoId}")  //productos GET por ID  en una tienda
	//URL: http://localhost:8080/whitecollar/products/shop/1/product/1
	//NOTA sobre la BBDD y la URL (PRODUCTOS asignados a cada TIENDA)
		//BBDD: 
			//En data.sql (resources) se han creado tiendas con tiendaId 1, 2 y 3
			//También se han creado 30 productos asignados a diferentes tiendas
				//Productos con productoId 1 a 10 corresponden a tiendaId 1
				//Productos con productoId 11 a 20 corresponden a tiendaId 2
				//Productos con productoId 21 a 30 corresponden a tiendaId 3
		//URL:
			//En ControllerRest.java hemos creado este RequestMapping
			//Incluye como variables tiendaId y productoId
			//Puede ocurrir que introduzcamos un productoId que no se corresponde a la tiendaId asignada
			//Por ejemplo, en lugar de solicitar en la URL la tiendaId=1 y el productoId=1...
			//... solicitar la tiendaId=2 y el productoId=1
			//En ese caso la variable que manda es el productoId, y mostrará la tiendaId correcta según BBDD
		
	//Estamos poniendo 2 variables: tiendaId y productoId
	public ResponseEntity<Producto> getProductoByIdTienda(@PathVariable("productoId") Long productoId) {
		Optional<Producto> optionalProducto = productosDAO.findById(productoId);
		if (optionalProducto.isPresent()) {
			return ResponseEntity.ok(optionalProducto.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping(value="products/shop/{tiendaId}") //productos POST (insertar nuevo producto en una tienda)
	public ResponseEntity<Producto> crearProductoTienda(@RequestBody Producto producto) {
		Producto newProducto = productosDAO.save(producto);
		return ResponseEntity.ok(newProducto);
	}
	
	@PutMapping(value="products/shop/{tiendaId}") //productos PUT (modificar producto existente en una tienda)
	public ResponseEntity<Producto> updateProductoTienda(@RequestBody Producto producto) {
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
	
	@DeleteMapping(value="products/shop/{tiendaId}/product/{productoId}") //productos DELETE (eliminar producto existente en una tienda)
	public ResponseEntity<Void> deleteProductosTienda(@PathVariable("productoId") Long productoId) {
		productosDAO.deleteById(productoId);
		return ResponseEntity.ok(null);
	}
	
	//MÉTODOS CRUD PARA CONSULTAR LOS PRODUCTOS (SUELTOS)
	//Extra: obtenemos listados de productos sin filtrar por tienda
	//No lo pide el ejercicio pero pongamos que...
	//Supongamos que White Collar S.A. es una franquicia y tienen una central donde analizan datos de todas las tiendas
	
	
	@GetMapping("products") //productos GET todos
	//URL: http://localhost:8080/whitecollar/products/
	public ResponseEntity<List<Producto>> getProducto() {
		List<Producto> productos = productosDAO.findAll();
		return ResponseEntity.ok(productos);
	}
	
	@RequestMapping(value="products/{productoId}")  //productos GET por ID
	//URL: http://localhost:8080/whitecollar/products/1
	public ResponseEntity<Producto> getProductoById(@PathVariable("productoId") Long productoId) {
		Optional<Producto> optionalProducto = productosDAO.findById(productoId);
		if (optionalProducto.isPresent()) {
			return ResponseEntity.ok(optionalProducto.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("products") //productos POST (insertar nuevo producto)
	public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
		Producto newProducto = productosDAO.save(producto);
		return ResponseEntity.ok(newProducto);
	}
	
	@PutMapping("products") //productos PUT (modificar producto existente)
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
	
	@DeleteMapping(value="products/{productoId}") //productos DELETE (eliminar producto existente)
	public ResponseEntity<Void> deleteProductos(@PathVariable("productoId") Long productoId) {
		productosDAO.deleteById(productoId);
		return ResponseEntity.ok(null);
	}
}

