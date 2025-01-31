package com.mecze.team.serwice;

import com.mecze.team.entity.Zawodnik;
import com.mecze.team.entity.Druzyna;
import com.mecze.team.repository.ZawodnikRepository;
import com.mecze.team.repository.DruzynyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZawodnikService {

    @Autowired
    private ZawodnikRepository zawodnikRepository;


    public ZawodnikService(ZawodnikRepository zawodnikRepository) {
        this.zawodnikRepository = zawodnikRepository;
    }

    public void update(Zawodnik zawodnik) {
        // Możesz dodać logikę walidacji tutaj, jeśli jest potrzebna
        zawodnikRepository.save(zawodnik);
    }
    public Zawodnik saveZawodnik(Zawodnik zawodnik) {
        return zawodnikRepository.save(zawodnik);
    }

    public List<Zawodnik> getAllZawodnicy() {
        return zawodnikRepository.findAll();
    }

    public Optional<Zawodnik> getZawodnikById(Long id) {
        return zawodnikRepository.findById(id);
    }

    public void deleteZawodnik(Long id) {
        zawodnikRepository.deleteById(id);
    }
}
