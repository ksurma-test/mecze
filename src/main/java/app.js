// app.js
const express = require('express');
const pool = require('./dbConfig');
const app = express();
const PORT = 3000;

// Ustawienie katalogu publicznego dla plików frontend
app.use(express.static('public'));

// Endpoint do pobierania wyników
app.get('/api/wyniki', async (req, res) => {
    const query = `
        SELECT
            d.id AS id_druzyny,
            d.name AS nazwa_druzyny,
            COUNT(CASE WHEN s.status = 'ZAKONCZONO' THEN 1 END) AS mecze_rozegrane,
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
        FROM
            druzyna d
        LEFT JOIN
            spotkania s ON d.id = s.druzyna1_id OR d.id = s.druzyna2_id
        GROUP BY
            d.id, d.name
        ORDER BY
            punkty DESC, wygrane DESC;
    `;

    try {
        const result = await pool.query(query);
        res.json(result.rows);
    } catch (err) {
        console.error('Błąd podczas wykonywania zapytania:', err);
        res.status(500).json({ error: 'Błąd serwera' });
    }
});

// Uruchomienie serwera
app.listen(PORT, () => {
    console.log(`Serwer działa na porcie ${PORT}`);
});
