package com.mecze.team.repository;

import com.mecze.team.entity.Druzyna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DruzynyRepository extends JpaRepository<Druzyna, Long> {
}

