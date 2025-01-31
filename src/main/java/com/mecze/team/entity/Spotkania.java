package com.mecze.team.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Spotkania {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "druzyna1_id", nullable = false)
    private Druzyna druzyna1;

    @ManyToOne
    @JoinColumn(name = "druzyna2_id", nullable = false)
    private Druzyna druzyna2;

    private Integer gole1;
    private Integer gole2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Druzyna getDruzyna1() {
        return druzyna1;
    }

    public void setDruzyna1(Druzyna druzyna1) {
        this.druzyna1 = druzyna1;
    }

    public Druzyna getDruzyna2() {
        return druzyna2;
    }

    public void setDruzyna2(Druzyna druzyna2) {
        this.druzyna2 = druzyna2;
    }

    public Integer getGole1() {
        return gole1;
    }

    public void setGole1(Integer gole1) {
        this.gole1 = gole1;
    }

    public Integer getGole2() {
        return gole2;
    }

    public void setGole2(Integer gole2) {
        this.gole2 = gole2;
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
