package com.mecze.team.serwice;


import com.mecze.team.entity.Spotkania;
import com.mecze.team.repository.SpotkaniaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpotkaniaService {

    private final SpotkaniaRepository spotkaniaRepository;

    public SpotkaniaService(SpotkaniaRepository spotkaniaRepository) {
        this.spotkaniaRepository = spotkaniaRepository;
    }

    public List<Spotkania> findAll() {
        return spotkaniaRepository.findAll();
    }

    public Spotkania save(Spotkania spotkania) {
        return spotkaniaRepository.save(spotkania);
    }

    public void deleteById(Long id) {
        spotkaniaRepository.deleteById(id);
    }

    public Spotkania findById(Long id) {
        return spotkaniaRepository.findById(id).orElse(null);
    }
}

