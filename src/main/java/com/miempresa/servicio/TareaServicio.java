package com.miempresa.servicio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.miempresa.interfaceServicio.ITareaServicio;
import com.miempresa.interfaces.ITarea;
import com.miempresa.modelo.Tarea;

@Service
public class TareaServicio implements ITareaServicio {
	
	@Autowired
	private ITarea repositorio;
	
	@Override
	public List<Tarea> listar() {
		return (List<Tarea>) repositorio.findAll();
	}

	@Override
	public Optional<Tarea> listarId(int id) {
		return repositorio.findById(id);
	}

	@Override
	public int guardar(Tarea p) {
		Tarea em = repositorio.save(p);
		if(!em.equals(null)) {
			return 1;
		}
		return 0;
	}

	@Override
	public void borrar(int id) {
		repositorio.deleteById(id);
		
	}
	
	@Override
	public List<Tarea> buscarPorDescripcion(String descripcion) {
		return repositorio.findByDescripcionContainingIgnoreCase(descripcion);
	}
}
