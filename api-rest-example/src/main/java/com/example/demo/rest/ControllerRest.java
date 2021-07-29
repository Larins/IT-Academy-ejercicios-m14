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
import com.example.demo.dao.TiendasDAO;
import com.example.demo.models.Producto;
import com.example.demo.models.Tienda;


@RestController //Esta clase va a ser un servicio REST
@RequestMapping("/whitecollar") //En esta URL se van a exponer los servicios de esta clase
public class ControllerRest {

	@Autowired //Inyección de dependencias
	private TiendasDAO tiendasDAO;

	//METODOS CRUD PARA LAS TIENDAS
	
	@GetMapping("shops") //tiendas GET todas
	public ResponseEntity<List<Tienda>> getTiendas() {
		List<Tienda> tiendas = tiendasDAO.findAll();
		return ResponseEntity.ok(tiendas);
	}
	
	@RequestMapping(value="shops/{tiendaId}")  //tiendas GET por ID
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
	
	//MÉTODOS CRUD PARA LOS PRODUCTOS (SUELTOS)
	//Lo que pide el ejercicio: extraer, por ejemplo, listados de productos por tienda
	
	@Autowired //Inyección de dependencias
	private ProductosDAO productosDAO;
	
	@GetMapping("shops/products") //productos GET todos
	public ResponseEntity<List<Producto>> getProductoTienda() {
		List<Producto> productos = productosDAO.findAll();
		return ResponseEntity.ok(productos);
	}
	
	@RequestMapping(value="shops/products/{productoId}")  //productos GET por ID
	public ResponseEntity<Producto> getProductoByIdTienda(@PathVariable("productoId") Long productoId) {
		Optional<Producto> optionalProducto = productosDAO.findById(productoId);
		if (optionalProducto.isPresent()) {
			return ResponseEntity.ok(optionalProducto.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("shops/products") //productos POST (insertar nuevo producto)
	public ResponseEntity<Producto> crearProductoTienda(@RequestBody Producto producto) {
		Producto newProducto = productosDAO.save(producto);
		return ResponseEntity.ok(newProducto);
	}
	
	@PutMapping("shops/products") //productos PUT (modificar producto existente)
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
	
	@DeleteMapping(value="shops/products/{productoId}") //productos DELETE (eliminar producto existente)
	public ResponseEntity<Void> deleteProductosTienda(@PathVariable("productoId") Long productoId) {
		productosDAO.deleteById(productoId);
		return ResponseEntity.ok(null);
	}
	
	//MÉTODOS CRUD PARA LOS PRODUCTOS (SUELTOS)
	//Supongamos que White Collar S.A. es una franquicia y tienen una central donde analizan datos de todas las tiendas
	//Respecto al apartado anterior, solo cambian las URL
	
	
	@GetMapping("products") //productos GET todos
	public ResponseEntity<List<Producto>> getProducto() {
		List<Producto> productos = productosDAO.findAll();
		return ResponseEntity.ok(productos);
	}
	
	@RequestMapping(value="products/{productoId}")  //productos GET por ID
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

