// dbConfig.js
const { Pool } = require('pg');

const pool = new Pool({
    user: 'postgres',        // Użytkownik PostgreSQL
    host: 'localhost',       // Adres hosta PostgreSQL
    database: 'mecze', // Nazwa bazy danych
    password: 'root',    // Hasło użytkownika
    port: 5433,              // Domyślny port PostgreSQL
});

module.exports = pool;
