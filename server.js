const express = require("express");
const { Pool } = require("pg");

const app = express();
const PORT = process.env.PORT || 3000; // Heroku dostarcza PORT jako zmienną środowiskową


const path = require('path');



// Konfiguracja połączenia z PostgreSQL (Heroku)
const pool = new Pool({
  connectionString: process.env.DATABASE_URL, // Heroku ustawia zmienną DATABASE_URL
  ssl: {
    rejectUnauthorized: false, // Wymagane dla Heroku, jeśli baza używa SSL
  },
});

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


// Trasa dla strony HTML /home
app.get('/home', (req, res) => {
    try {
        res.sendFile(path.join(__dirname, 'templates', 'home.html'));  // Zaktualizowana ścieżka
    } catch (error) {
        console.error("Błąd przy ładowaniu home.html:", error);
        res.status(500).send("Wystąpił błąd przy ładowaniu strony.");
    }
});


// Sprawdzenie połączenia z bazą
pool.connect()
  .then(() => console.log("Połączono z bazą danych PostgreSQL"))
  .catch(err => console.error("Błąd połączenia z bazą:", err));


// Nasłuchiwanie na porcie
app.listen(PORT, () => {
  console.log(`Serwer działa na porcie ${PORT}`);
});
