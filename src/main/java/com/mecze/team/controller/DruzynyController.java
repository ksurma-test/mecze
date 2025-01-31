package com.mecze.team.controller;


import com.mecze.team.entity.Druzyna;
import com.mecze.team.repository.DruzynyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/druzyny")
public class DruzynyController {

    @Autowired
    private DruzynyRepository druzynyRepository;

    // Wyświetlanie wszystkich drużyn
    @GetMapping
    public String index(Model model) {
        List<Druzyna> druzyny = druzynyRepository.findAll();
        model.addAttribute("druzyny", druzyny);
        return "druzyny/index";  // strona z tabelą drużyn
    }

    // Wyświetlanie formularza dodawania drużyny
    @GetMapping("/create")
    public String createDruzynaForm(Model model) {
        model.addAttribute("druzyna", new Druzyna());
        return "druzyny/create";  // strona z formularzem dodawania
    }

    // Dodawanie drużyny
    @PostMapping("/create")
    public String createDruzyna(@ModelAttribute Druzyna druzyna) {
        druzynyRepository.save(druzyna);
        return "redirect:/druzyny";  // przekierowanie do listy drużyn
    }

    // Wyświetlanie formularza edycji drużyny
    @GetMapping("/edit/{id}")
    public String editDruzynaForm(@PathVariable Long id, Model model) {
        Druzyna druzyna = druzynyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono drużyny o ID: " + id));
        model.addAttribute("druzyna", druzyna);
        return "druzyny/edit";  // strona z formularzem edytowania
    }

    // Edytowanie drużyny
    @PostMapping("/edit/{id}")
    public String editDruzyna(@PathVariable Long id, @ModelAttribute Druzyna druzyna) {
        druzyna.setId(id);
        druzynyRepository.save(druzyna);
        return "redirect:/druzyny";  // przekierowanie do listy drużyn
    }

    // Usuwanie drużyny
    @GetMapping("/delete/{id}")
    public String deleteDruzyna(@PathVariable Long id) {
        druzynyRepository.deleteById(id);
        return "redirect:/druzyny";  // przekierowanie do listy drużyn
    }
}
