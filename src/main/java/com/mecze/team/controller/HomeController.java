package com.mecze.team.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        // Możesz dodać dane do modelu, jeśli potrzebujesz
        return "home"; // Wskazuje na templates/home/home.html
    }
}
