# Pobranie oficjalnego obrazu JDK 17
FROM eclipse-temurin:17-jdk AS build

# Ustawienie katalogu roboczego w kontenerze
WORKDIR /app

# Kopiowanie aplikacji JAR do kontenera
COPY target/mecze-0.0.1-SNAPSHOT.jar app.jar

# Ustawienie portu, na którym działa aplikacja
EXPOSE 8080

# Uruchomienie aplikacji
ENTRYPOINT ["java", "-jar", "app.jar"]
