package com.mercado.mercadosegundamano.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercado.mercadosegundamano.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findFirstByEmail(String email);

}
