package com.saberpro.controller;

import com.saberpro.model.Estudiante;
import com.saberpro.repository.EstudianteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteRepository estudianteRepo;

    public EstudianteController(EstudianteRepository estudianteRepo) {
        this.estudianteRepo = estudianteRepo;
    }

    @GetMapping
    public String listar(Model model) {
        List<Estudiante> lista = estudianteRepo.findAll();
        model.addAttribute("estudiantes", lista);
        return "estudiantes-list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiante-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Estudiante estudiante) {
        estudianteRepo.findByDocumento(estudiante.getDocumento())
                .ifPresent(existing -> estudiante.setId(existing.getId()));
        estudianteRepo.save(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        Estudiante estudiante = estudianteRepo.findById(id).orElse(new Estudiante());
        model.addAttribute("estudiante", estudiante);
        return "estudiante-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        if (estudianteRepo.existsById(id)) {
            estudianteRepo.deleteById(id);
        }
        return "redirect:/estudiantes";
    }
}
