package com.mecze.team.controller;

import com.mecze.team.entity.Zawodnik;
import com.mecze.team.serwice.DruzynyService;
import com.mecze.team.serwice.ZawodnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/zawodnicy")
public class ZawodnikController {

    @Autowired
    private ZawodnikService zawodnikService;

    @Autowired
    private DruzynyService druzynaService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("zawodnicy", zawodnikService.getAllZawodnicy());
        return "zawodnicy/index";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("zawodnik", new Zawodnik());
        model.addAttribute("druzyny", druzynaService.getAllDruzyny());
        return "zawodnicy/create";
    }

    @PostMapping("/create")
    public String createZawodnik(@ModelAttribute Zawodnik zawodnik) {
        zawodnikService.saveZawodnik(zawodnik);
        return "redirect:/zawodnicy";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Zawodnik zawodnik = zawodnikService.getZawodnikById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Zawodnik Id:" + id));
        model.addAttribute("zawodnik", zawodnik);
        model.addAttribute("druzyny", druzynaService.getAllDruzyny());
        return "zawodnicy/edit";
    }

    @PostMapping("/edit")
    public String updateZawodnik(@ModelAttribute Zawodnik zawodnik) {
        zawodnikService.update(zawodnik);
        return "redirect:/zawodnicy"; // Powrót do listy zawodników
    }

    @PostMapping("/update/{id}")
    public String updateZawodnik(@PathVariable Long id, @ModelAttribute Zawodnik zawodnik) {
        zawodnik.setId(id);
        zawodnikService.saveZawodnik(zawodnik);
        return "redirect:/zawodnicy";
    }

    @GetMapping("/delete/{id}")
    public String deleteZawodnik(@PathVariable Long id) {
        zawodnikService.deleteZawodnik(id);
        return "redirect:/zawodnicy";
    }
}
