package com.proyecto.daw.proyectodaw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
 
    @GetMapping("/")
    public String homeForm(Model model) {
        
        return "fanadesh-html/index";
    }
}
