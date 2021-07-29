package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Tienda;

public interface TiendasDAO extends JpaRepository<Tienda,Long> {


}
