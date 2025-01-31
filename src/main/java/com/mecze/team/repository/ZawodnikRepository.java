package com.mecze.team.repository;

import com.mecze.team.entity.Druzyna;
import com.mecze.team.entity.Zawodnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZawodnikRepository extends JpaRepository<Zawodnik, Long> {
}

