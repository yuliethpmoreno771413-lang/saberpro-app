package com.saberpro.controller;

import com.saberpro.repository.EstudianteRepository;
import com.saberpro.repository.ResultadoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador principal del sistema Saber Pro.
 * Gestiona la navegación hacia el panel principal y la sección de coordinadores.
 */
@Controller
public class HomeController {

    private final EstudianteRepository estudianteRepo;
    private final ResultadoRepository resultadoRepo;

    // 🔧 Constructor: inyecta los repositorios de estudiantes y resultados
    public HomeController(EstudianteRepository estudianteRepo, ResultadoRepository resultadoRepo) {
        this.estudianteRepo = estudianteRepo;
        this.resultadoRepo = resultadoRepo;
    }

    // 🧭 Ruta del panel principal (dashboard)
    @GetMapping({"/", "/panel"})
    public String panel(Model model) {
        model.addAttribute("totalEstudiantes", estudianteRepo.count());
        model.addAttribute("totalResultados", resultadoRepo.count());
        return "panel"; // Renderiza el archivo panel.html
    }

    // 👩‍💼 Ruta para la página de Coordinadores
    @GetMapping("/coordinadores")
    public String coordinadores(Model model) {
        model.addAttribute("mensaje", "Información para los coordinadores y los incentivos UTS Saber Pro.");
        return "coordinadores"; // Renderiza el archivo coordinadores.html
    }
}
