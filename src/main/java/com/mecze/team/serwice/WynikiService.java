package com.mecze.team.serwice;

import com.mecze.team.entity.WynikiDTO;
import com.mecze.team.repository.DruzynyRepository;
import com.mecze.team.repository.WynikiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WynikiService {

    private final WynikiRepository wynikiRepository;

    public WynikiService(WynikiRepository wynikiRepository) {
        this.wynikiRepository = wynikiRepository;
    }


    public List<WynikiDTO> getTabelaWynikow() {
        List<Object[]> wyniki = wynikiRepository.getTabelaWynikow();

        return wyniki.stream().map(w -> new WynikiDTO(
                ((Number) w[0]).longValue(),  // idDruzyny
                (String) w[1],               // nazwaDruzyny
                ((Number) w[2]).intValue(),  // meczeRozegrane
                ((Number) w[3]).intValue(),  // wygrane
                ((Number) w[4]).intValue(),  // przegrane
                ((Number) w[5]).intValue(),  // remisy
                ((Number) w[6]).intValue()   // punkty
        )).collect(Collectors.toList());
    }
}