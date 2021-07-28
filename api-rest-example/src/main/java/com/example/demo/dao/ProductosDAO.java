package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Producto;

public interface ProductosDAO extends JpaRepository<Producto,Long> {

}
