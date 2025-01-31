package com.mecze.team.repository;

import com.mecze.team.entity.Druzyna;
import com.mecze.team.entity.WynikiDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DruzynyRepository extends JpaRepository<Druzyna, Long> {


    @Query(value = "SELECT " +
            "d.name AS nazwa_druzyny, " +
            "COUNT(CASE WHEN s.status = 'ZAKONCZONO' THEN 1 END) AS mecze_rozegrane, " +
            "COUNT(CASE WHEN s.status = 'ZAKONCZONO' AND " +
            "( (s.druzyna1_id = d.id AND s.gole1 > s.gole2) OR " +
            "(s.druzyna2_id = d.id AND s.gole2 > s.gole1) ) THEN 1 END) AS wygrane, " +
            "COUNT(CASE WHEN s.status = 'ZAKONCZONO' AND " +
            "( (s.druzyna1_id = d.id AND s.gole1 < s.gole2) OR " +
            "(s.druzyna2_id = d.id AND s.gole2 < s.gole1) ) THEN 1 END) AS przegrane, " +
            "COUNT(CASE WHEN s.status = 'ZAKONCZONO' AND s.gole1 = s.gole2 THEN 1 END) AS remisy, " +
            "SUM(CASE WHEN s.status = 'ZAKONCZONO' THEN " +
            "CASE WHEN (s.druzyna1_id = d.id AND s.gole1 > s.gole2) OR " +
            "(s.druzyna2_id = d.id AND s.gole2 > s.gole1) THEN 3 " +
            "WHEN s.gole1 = s.gole2 THEN 1 ELSE 0 END " +
            "ELSE 0 END) AS punkty " +
            "FROM druzyna d " +
            "LEFT JOIN spotkania s ON d.id = s.druzyna1_id OR d.id = s.druzyna2_id " +
            "GROUP BY d.id, d.name " +
            "ORDER BY punkty DESC, wygrane DESC", nativeQuery = true)
    List<WynikiDTO> findWyniki();

}

