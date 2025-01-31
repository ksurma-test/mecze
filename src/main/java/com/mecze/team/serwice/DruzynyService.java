package com.mecze.team.serwice;

import com.mecze.team.entity.Druzyna;
import com.mecze.team.repository.DruzynyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DruzynyService {

    @Autowired
    private final DruzynyRepository druzynaRepository;

    public DruzynyService(DruzynyRepository druzynaRepository) {
        this.druzynaRepository = druzynaRepository;
    }

    public List<Druzyna> findAll() {
        return druzynaRepository.findAll();
    }

    public List<Druzyna> getAllDruzyny() {
        return druzynaRepository.findAll();
    }

    public Druzyna save(Druzyna druzyna) {
        return druzynaRepository.save(druzyna);
    }

    public Optional<Druzyna> findById(Long id) {
        return druzynaRepository.findById(id);
    }

    public void deleteById(Long id) {
        druzynaRepository.deleteById(id);
    }
}
