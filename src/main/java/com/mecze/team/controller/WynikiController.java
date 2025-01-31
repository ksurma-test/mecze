package com.mecze.team.controller;


import com.mecze.team.entity.WynikiDTO;
import com.mecze.team.repository.DruzynyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class WynikiController {

    @Autowired

    private DruzynyRepository druzynaRepository;

    @GetMapping("/wyniki")

    public List<WynikiDTO> getWyniki() {

        return druzynaRepository.findWyniki();
    }


}
