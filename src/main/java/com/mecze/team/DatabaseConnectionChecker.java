package com.mecze.team;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseConnectionChecker implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseConnectionChecker(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        checkConnection();
    }

    private void checkConnection() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null) {
                System.out.println("Połączenie z bazą danych nawiązane pomyślnie!");
            }
        } catch (SQLException e) {
            System.out.println("Błąd podczas nawiązywania połączenia z bazą danych!");
            e.printStackTrace();
        }
    }
}
