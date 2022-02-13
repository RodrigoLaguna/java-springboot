package mx.com.gm.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {
        
        // Inyeccion de dependencias @Inject
        @Autowired
        private IPersonaService personaService;

        @GetMapping("/")
        public String inicio(Model model, @AuthenticationPrincipal User user){
                log.info("usuario que hizo login " + user);
                var personas = personaService.listarPersonas();
                log.info("Ejecutando el controlador Spring MVC");
                model.addAttribute("personas",personas);
                return "index";
        }
        
        @GetMapping("/agregar")
        public String agregar(Persona persona){
                return "modificar";
        }
        
        @PostMapping("/guardar")
        public String guardar(@Valid Persona persona, Errors errors){
                // Validacion de datos
                if (errors.hasErrors()) {
                        return "modificar";
                }
                personaService.guardar(persona);
                return "redirect:/";
        }
        
        @GetMapping("/editar/{idPersona}")
        public String editar(Persona persona,Model model){
                persona = personaService.encontrar(persona);
                model.addAttribute(persona);
                return "modificar";
        }
        
        @GetMapping("/eliminar")
        public String eliminar(Persona persona){
                personaService.eliminar(persona);
                return "redirect:/";
        }
        
}
