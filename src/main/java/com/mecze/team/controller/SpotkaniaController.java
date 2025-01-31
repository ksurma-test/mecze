package com.mecze.team.controller;

import com.mecze.team.entity.Druzyna;
import com.mecze.team.entity.Spotkania;
import com.mecze.team.entity.Status;
import com.mecze.team.repository.DruzynyRepository;
import com.mecze.team.repository.SpotkaniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("/spotkania")
public class SpotkaniaController {


        @Autowired
        private SpotkaniaRepository spotkaniaRepository;

        @Autowired
        private DruzynyRepository druzynyRepository;

        // Wyświetlanie wszystkich spotkań
        @GetMapping
        public String index(Model model) {
            List<Spotkania> spotkania = spotkaniaRepository.findAll();
            model.addAttribute("spotkania", spotkania);
            return "spotkania/index";  // strona z tabelą spotkań
        }

        // Wyświetlanie formularza dodawania spotkania
        @GetMapping("/create")
        public String createSpotkanieForm(Model model) {
            model.addAttribute("spotkanie", new Spotkania());
            model.addAttribute("druzyny", druzynyRepository.findAll());
            model.addAttribute("statuses", Status.values());
            return "spotkania/create";  // strona z formularzem dodawania
        }

        // Dodawanie spotkania
        @PostMapping("/create")
        public String createSpotkanie(
                @RequestParam("data") LocalDate data,
                @RequestParam("druzyna1Id") Long druzyna1Id,
                @RequestParam("druzyna2Id") Long druzyna2Id,
                @RequestParam("gole1") int gole1,
                @RequestParam("gole2") int gole2,
                @RequestParam("status") Status status) {

            Druzyna druzyna1 = druzynyRepository.findById(druzyna1Id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Team ID for Drużyna 1"));
            Druzyna druzyna2 = druzynyRepository.findById(druzyna2Id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Team ID for Drużyna 2"));

            Spotkania spotkanie = new Spotkania();
            spotkanie.setData(data);
            spotkanie.setDruzyna1(druzyna1);
            spotkanie.setDruzyna2(druzyna2);
            spotkanie.setGole1(gole1);
            spotkanie.setGole2(gole2);
            spotkanie.setStatus(status);

            spotkaniaRepository.save(spotkanie);
            return "redirect:/spotkania";
        }


        // Wyświetlanie formularza edytowania spotkania
        @GetMapping("/edit/{id}")
        public String editSpotkanieForm(@PathVariable Long id, Model model) {
            Spotkania spotkanie = spotkaniaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono spotkania o podanym ID: " + id));

            System.out.println("Loaded Spotkanie: " + spotkanie); // Logowanie do sprawdzenia danych
            // Konwersja daty na format dd-MM-yyyy
     //       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    //        String formattedDate = spotkanie.getData().format(formatter);
            List<Druzyna> druzyny = druzynyRepository.findAll();

            model.addAttribute("spotkanie", spotkanie);
            model.addAttribute("druzyny", druzyny);
   //         model.addAttribute("formattedDate", formattedDate);
            return "spotkania/edit";
        }



        // Edytowanie spotkania
        @Transactional
    @PostMapping("/edit")
        public String updateSpotkanie(@RequestParam Long id, @ModelAttribute Spotkania spotkanie) {
            // Sprawdzenie, czy spotkanie istnieje
            System.out.println("Updating spotkanie with ID: " + id);
            System.out.println("Spotkanie: " + spotkanie);

            Spotkania existingSpotkanie = spotkaniaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Spotkanie o podanym ID nie zostało znalezione"));

            // Ustawiamy zmienione pola
            existingSpotkanie.setData(spotkanie.getData());
            existingSpotkanie.setDruzyna1(spotkanie.getDruzyna1());
            existingSpotkanie.setDruzyna2(spotkanie.getDruzyna2());
            existingSpotkanie.setGole1(spotkanie.getGole1());
            existingSpotkanie.setGole2(spotkanie.getGole2());
            existingSpotkanie.setStatus(spotkanie.getStatus());


            System.out.println("Data: " + spotkanie.getData());
            System.out.println("Drużyna 1 ID: " + spotkanie.getDruzyna1().getId());
            System.out.println("Drużyna 2 ID: " + spotkanie.getDruzyna2().getId());
            System.out.println("Gole 1: " + spotkanie.getGole1());
            System.out.println("Gole 2: " + spotkanie.getGole2());
            System.out.println("Status: " + spotkanie.getStatus());


            // Zapisujemy zmiany
            try {
                spotkaniaRepository.save(spotkanie);
                System.out.println("Spotkanie zaktualizowane pomyślnie");
            } catch (Exception e) {
                System.err.println("Błąd podczas aktualizacji spotkania: " + e.getMessage());
                e.printStackTrace();
            }

            return "redirect:/spotkania"; // Przekierowanie do listy spotkań
        }



    // Usuwanie spotkania
        @GetMapping("/delete/{id}")
        public String deleteSpotkanie(@PathVariable Long id) {
            spotkaniaRepository.deleteById(id);
            return "redirect:/spotkania";  // przekierowanie do listy spotkań
        }
    }
