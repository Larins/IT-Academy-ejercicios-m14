package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Producto;

public interface ProductosDAO extends JpaRepository<Producto,Long> {
	//List<Producto> findProductoByshopid(Long shopid);
	List<Producto> findAllByshopid(Long shopid);
}
