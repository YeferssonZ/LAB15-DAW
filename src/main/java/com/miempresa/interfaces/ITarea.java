package com.miempresa.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.miempresa.modelo.Tarea;
import java.util.List;

@Repository
public interface ITarea extends CrudRepository<Tarea, Integer> {
    List<Tarea> findByDescripcionContainingIgnoreCase(String descripcion);
}