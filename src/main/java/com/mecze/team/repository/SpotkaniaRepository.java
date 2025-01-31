package com.mecze.team.repository;

import com.mecze.team.entity.Spotkania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotkaniaRepository extends JpaRepository<Spotkania, Long> {}


