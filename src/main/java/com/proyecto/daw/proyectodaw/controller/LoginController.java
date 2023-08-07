package com.proyecto.daw.proyectodaw.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.daw.proyectodaw.dto.UserDto;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userDto", UserDto.builder().build());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result) {
        /*if (result.hasErrors()) {
            return "login";
        }*/
        // Aquí puedes implementar la lógica para autenticar al usuario
        // y redirigir a la página correspondiente después del inicio de sesión exitoso.
        return "redirect:/home"; // Ejemplo: redirigir a la página de inicio (home).
    }
}
