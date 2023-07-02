package com.miempresa.interfaceServicio;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.miempresa.modelo.Tarea;

@Service
public interface ITareaServicio {
    public List<Tarea> listar();
    public Optional<Tarea> listarId(int id);
    public int guardar(Tarea p);
    public void borrar(int id);
    List<Tarea> buscarPorDescripcion(String descripcion);
}