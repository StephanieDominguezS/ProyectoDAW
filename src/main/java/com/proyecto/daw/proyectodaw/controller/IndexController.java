package com.proyecto.daw.proyectodaw.controller;

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final ResourceBundleMessageSource messageSource;

    public IndexController(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }
 
    @GetMapping("/")
    public String homeForm(Model model) {

        Locale locale = Locale.getDefault();
        
        model.addAttribute("pageTitle", messageSource.getMessage("pageTitle", null, locale));
        model.addAttribute("homeHeroTitle", messageSource.getMessage("home.hero.title", null, locale));
        model.addAttribute("homeHeroSubtitle", messageSource.getMessage("home.hero.subtitle", null, locale));

        
        return "fanadesh-html/index";
    }

    @GetMapping("/about")
    public String aboutForm(Model model) {

        Locale locale = Locale.getDefault();
        
        model.addAttribute("footer", messageSource.getMessage("footer.logoText", null, locale));
        model.addAttribute("pageTitle", messageSource.getMessage("pageTitle", null, locale));
        model.addAttribute("homeHeroTitle", messageSource.getMessage("home.hero.title", null, locale));
        model.addAttribute("homeHeroSubtitle", messageSource.getMessage("home.hero.subtitle", null, locale));


        
        return "fanadesh-html/about";
    }

    @GetMapping("/admission")
    public String admissionForm(Model model) {
        
        return "fanadesh-html/admission";
    }

    @GetMapping("/why")
    public String whyForm(Model model) {
        
        return "fanadesh-html/why";
    }

    @GetMapping("/contact")
    public String contactForm(Model model) {
        
        return "fanadesh-html/contact";
    }
}
