const express = require("express");
const { Pool } = require("pg");

const app = express();
const PORT = process.env.PORT || 3000; // Heroku dostarcza PORT jako zmienną środowiskową

// Konfiguracja połączenia z PostgreSQL (Heroku)
const pool = new Pool({
  connectionString: process.env.DATABASE_URL, // Heroku ustawia zmienną DATABASE_URL
  ssl: {
    rejectUnauthorized: false, // Wymagane dla Heroku, jeśli baza używa SSL
  },
});

// Sprawdzenie połączenia z bazą
pool.connect()
  .then(() => console.log("Połączono z bazą danych PostgreSQL"))
  .catch(err => console.error("Błąd połączenia z bazą:", err));

// Endpoint testowy
app.get("/", (req, res) => {
  res.send("Serwer działa!");
});

// Nasłuchiwanie na porcie
app.listen(PORT, () => {
  console.log(`Serwer działa na porcie ${PORT}`);
});
