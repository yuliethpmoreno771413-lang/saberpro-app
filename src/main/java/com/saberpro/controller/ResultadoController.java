package com.saberpro.controller;

import com.saberpro.model.Resultado;
import com.saberpro.model.Materia;
import com.saberpro.model.Estudiante;
import com.saberpro.repository.ResultadoRepository;
import com.saberpro.repository.EstudianteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;

@Controller
@RequestMapping("/resultados")
public class ResultadoController {

    private final ResultadoRepository resultadoRepo;
    private final EstudianteRepository estudianteRepo;

    public ResultadoController(ResultadoRepository r, EstudianteRepository e) {
        this.resultadoRepo = r;
        this.estudianteRepo = e;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("resultados", resultadoRepo.findAll());
        return "resultados-list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        Resultado res = new Resultado();

        // Materias predefinidas
        res.setMaterias(Arrays.asList(
                new Materia("Comunicación Escrita", 0, 0),
                new Materia("Razonamiento Cuantitativo", 0, 0),
                new Materia("Lectura Crítica", 0, 0),
                new Materia("Competencias Ciudadanas", 0, 0),
                new Materia("Inglés", 0, 0),
                new Materia("Formulación de Proyectos", 0, 0),
                new Materia("Pensamiento Científico", 0, 0),
                new Materia("Diseño de Software", 0, 0)
        ));

        model.addAttribute("resultado", res);
        model.addAttribute("estudiantes", estudianteRepo.findAll());
        return "resultado-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Resultado resultado) {
        // Calcular promedio
        double total = resultado.getMaterias().stream()
                .mapToDouble(Materia::getPuntaje)
                .average()
                .orElse(0);

        resultado.setPuntajeTotal(total);
        resultado.setFecha(LocalDate.now());
        resultadoRepo.save(resultado);
        return "redirect:/resultados";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable String id, Model model) {
        Resultado r = resultadoRepo.findById(id).orElse(null);
        if (r == null) return "redirect:/resultados";

        Estudiante e = estudianteRepo.findById(r.getEstudianteId()).orElse(new Estudiante());
        model.addAttribute("resultado", r);
        model.addAttribute("estudiante", e);
        model.addAttribute("estado", calcularEstado(r.getPuntajeTotal()));
        return "resultado-ver";
    }

    private String calcularEstado(double puntaje) {
        if (puntaje < 180) return "No alcanzó nivel";
        if (puntaje < 210) return "Básico";
        if (puntaje < 240) return "Competente";
        return "Avanzado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        resultadoRepo.deleteById(id);
        return "redirect:/resultados";
    }
}
