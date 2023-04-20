package com.mercado.mercadosegundamano.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.mercadosegundamano.modelo.Compra;
import com.mercado.mercadosegundamano.modelo.Producto;
import com.mercado.mercadosegundamano.modelo.Usuario;
import com.mercado.mercadosegundamano.repositorios.CompraRepository;

@Service
public class CompraServicio {
	
	@Autowired
	CompraRepository repositorio;
	
	@Autowired
	ProductoServicio productoServicio;
	
	public Compra insertar(Compra c, Usuario u) {
		c.setPropietario(u);
		return repositorio.save(c);
	}
	
	public Compra insertar(Compra c) {
		return repositorio.save(c);
	}
	
	public Producto addProductoCompra(Producto p, Compra c) {
		p.setCompra(c);
		return productoServicio.editar(p);
	}
	
	public Compra buscarPorId(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	public List<Compra> todas() {
		return repositorio.findAll();
	}
	
	public List<Compra> porPropietario(Usuario u) {
		return repositorio.findByPropietario(u);
	}
	
}
