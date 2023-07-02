package com.miempresa.modelo;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="Empleado")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "rol")
	private String rol;
	
	@OneToMany
	private List<Tarea> tarea;
	
	

	public Empleado() {
	}

	public Empleado(int id, String nombre, String rol, List<Tarea> tarea) {
		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
		this.tarea = tarea;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Tarea> getTarea() {
		return tarea;
	}

	public void setTarea(List<Tarea> tarea) {
		this.tarea = tarea;
	}
}
