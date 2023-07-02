package com.miempresa.interfaceServicio;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.miempresa.modelo.Empleado;

@Service
public interface IEmpleadoServicio {
	public List<Empleado> listar();
	public Optional<Empleado> listarId(int id);
	public int guardar(Empleado p);
	public void borrar(int id);
	List<Empleado> buscarPorNombre(String nombre);
}