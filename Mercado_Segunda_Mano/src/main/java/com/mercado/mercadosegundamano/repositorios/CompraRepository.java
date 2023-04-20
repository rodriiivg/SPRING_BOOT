package com.mercado.mercadosegundamano.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercado.mercadosegundamano.modelo.Compra;
import com.mercado.mercadosegundamano.modelo.Usuario;

public interface CompraRepository extends JpaRepository<Compra, Long>{

	List<Compra> findByPropietario(Usuario propietario);
	
}
