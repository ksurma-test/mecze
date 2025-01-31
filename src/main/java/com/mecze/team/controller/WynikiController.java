package com.mecze.team.controller;


import com.mecze.team.entity.WynikiDTO;
import com.mecze.team.repository.DruzynyRepository;
import com.mecze.team.serwice.WynikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wyniki")

public class WynikiController {

    private final WynikiService wynikiService;

    public WynikiController(WynikiService wynikiService) {
        this.wynikiService = wynikiService;
    }

    @GetMapping
    public List<WynikiDTO> getWyniki() {
        return wynikiService.getTabelaWynikow();
    }
}
