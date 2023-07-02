package com.miempresa.controlador;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.interfaceServicio.*;
import com.miempresa.modelo.*;

@Controller
@RequestMapping
public class controlador {

    @Autowired
    private IEmpleadoServicio servicio;

    @GetMapping("/listarEmpleados")
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = servicio.listar();
        model.addAttribute("empleados", empleados);
        model.addAttribute("tabla", "empleados");
        return "empleados";
    }

    @Autowired
    private ITareaServicio servicioTarea;

    @GetMapping("/listarTareas")
    public String listarTareas(Model model) {
        List<Tarea> tareas = servicioTarea.listar();
        model.addAttribute("tareas", tareas);
        model.addAttribute("tabla", "tareas");
        return "tareas";
    }

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    @GetMapping("/buscarEmpleado")
    public String buscarEmpleado(@RequestParam("nombre") String nombre, @RequestParam(
    		value = "tabla", required = false) String tabla, Model model) {
        List<Empleado> empleadosEncontrados = empleadoServicio.buscarPorNombre(nombre);

        if (empleadosEncontrados.isEmpty()) {
            model.addAttribute("mensaje", "No se encontraron coincidencias");
        } else {
            model.addAttribute("empleados", empleadosEncontrados);
        }

        if (tabla != null && tabla.equals("empleados")) {
            model.addAttribute("tabla", "empleados");
        }
        model.addAttribute("nombre", nombre);

        return "buscarEmpleado";
    }


    @Autowired
    private ITareaServicio tareaServicio;

    @GetMapping("/buscarTarea")
    public String buscarTarea(@RequestParam("nombre") String nombre, @RequestParam(
    		value = "tabla", required = false) String tabla, Model model) {
        List<Tarea> tareasEncontradas = tareaServicio.buscarPorDescripcion(nombre);

        if (tareasEncontradas.isEmpty()) {
            model.addAttribute("mensaje", "No se encontraron coincidencias");
        } else {
            model.addAttribute("tareas", tareasEncontradas);
        }

        if (tabla != null && tabla.equals("tareas")) {
            model.addAttribute("tabla", "tareas");
        }
        model.addAttribute("nombre", nombre);

        return "buscarTarea";
    }
    
  //APARTADO PARA EMPLEADOS
    
    @GetMapping("/agregarEmpleado")
    public String agregarEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "agregarEmpleado";
    } 
    
    @PostMapping("/guardarEmpleado")
    public String guardarEmpleado(Empleado p) {
        servicio.guardar(p);
        return "redirect:/listarEmpleados";
    }
    
    @GetMapping("/editarEmpleado/{id}")
    public String editarEmpleado(@PathVariable int id, RedirectAttributes atributos) {
        Optional<Empleado> empleado = servicio.listarId(id);
        atributos.addFlashAttribute("empleado", empleado);
        return "redirect:/mostrarEmpleado";
    }
    
    @GetMapping("/mostrarEmpleado")
    public String mostrarEmpleado(@ModelAttribute("empleado") Empleado p, Model model) {
        model.addAttribute("empleado", p);
        return "agregarEmpleado";
    } 
    
    @GetMapping("/eliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable int id ) {
    	servicio.borrar(id);
    	return "redirect:/listarEmpleados";
    }
    
    //APARTADO PARA TAREAS
    
        @GetMapping("/agregarTarea")
    public String agregarTarea(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "agregarTarea";
    } 
    
    @PostMapping("/guardarTarea")
    public String guardarTarea(Tarea p) {
    	tareaServicio.guardar(p);
        return "redirect:/listarTareas";
    }
    
    @GetMapping("/editarTarea/{id}")
    public String editarTarea(@PathVariable int id, RedirectAttributes atributos) {
        Optional<Tarea> tarea = tareaServicio.listarId(id);
        atributos.addFlashAttribute("tarea", tarea);
        return "redirect:/mostrarTarea";
    }
    
    @GetMapping("/mostrarTarea")
    public String mostrarTarea(@ModelAttribute("tarea") Tarea p, Model model) {
        model.addAttribute("tarea", p);
        return "agregarTarea";
    } 
    
    @GetMapping("/eliminarTarea/{id}")
    public String eliminarTarea(@PathVariable int id ) {
    	tareaServicio.borrar(id);
    	return "redirect:/listarTareas";
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/logout-success";
    }

    @GetMapping("/logout-success")
    public String logoutSuccess(Model model) {
        model.addAttribute("mensaje", "Has cerrado sesión exitosamente");
        return "logout";
    }
}