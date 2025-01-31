package com.mecze.team.repository;

import com.mecze.team.entity.Druzyna;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WynikiRepository extends CrudRepository <Druzyna, Long> {


    @Query(value = """
        SELECT
            d.id AS idDruzyny,
            d.name AS nazwaDruzyny,
            COUNT(CASE WHEN s.status = 'ZAKONCZONO' THEN 1 END) AS meczeRozegrane,
            COUNT(CASE WHEN s.status = 'ZAKONCZONO' AND (
                            (s.druzyna1_id = d.id AND s.gole1 > s.gole2) OR
                            (s.druzyna2_id = d.id AND s.gole2 > s.gole1)
                        ) THEN 1 END) AS wygrane,
            COUNT(CASE WHEN s.status = 'ZAKONCZONO' AND (
                            (s.druzyna1_id = d.id AND s.gole1 < s.gole2) OR
                            (s.druzyna2_id = d.id AND s.gole2 < s.gole1)
                        ) THEN 1 END) AS przegrane,
            COUNT(CASE WHEN s.status = 'ZAKONCZONO' AND s.gole1 = s.gole2 THEN 1 END) AS remisy,
            SUM(CASE WHEN s.status = 'ZAKONCZONO' THEN
                        CASE WHEN (s.druzyna1_id = d.id AND s.gole1 > s.gole2) OR
                                  (s.druzyna2_id = d.id AND s.gole2 > s.gole1) THEN 3
                             WHEN s.gole1 = s.gole2 THEN 1
                             ELSE 0
                        END
                    ELSE 0 END) AS punkty
        FROM druzyna d
        LEFT JOIN spotkania s ON d.id = s.druzyna1_id OR d.id = s.druzyna2_id
        GROUP BY d.id, d.name
        ORDER BY punkty DESC, wygrane DESC
    """, nativeQuery = true)
    List<Object[]> getTabelaWynikow();
}
