package com.miempresa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miempresa.modelo.Usuario;

public interface IUsuario extends JpaRepository<Usuario, Integer>{
	Usuario findByNombre(String nombre);

}
