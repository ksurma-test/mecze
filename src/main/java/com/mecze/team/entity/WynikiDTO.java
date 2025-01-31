package com.mecze.team.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WynikiDTO {
    @JsonProperty("idDruzyny")
    private Long idDruzyny;

    @JsonProperty("nazwaDruzyny")
    private String nazwaDruzyny;

    @JsonProperty("meczeRozegrane")
    private int meczeRozegrane;

    @JsonProperty("wygrane")
    private int wygrane;

    @JsonProperty("przegrane")
    private int przegrane;

    @JsonProperty("remisy")
    private int remisy;

    @JsonProperty("punkty")
    private int punkty;

    // Konstruktor
    public WynikiDTO(Long idDruzyny, String nazwaDruzyny, int meczeRozegrane, int wygrane, int przegrane, int remisy, int punkty) {
        this.idDruzyny = idDruzyny;
        this.nazwaDruzyny = nazwaDruzyny;
        this.meczeRozegrane = meczeRozegrane;
        this.wygrane = wygrane;
        this.przegrane = przegrane;
        this.remisy = remisy;
        this.punkty = punkty;
    }

    // Gettery
    public Long getIdDruzyny() { return idDruzyny; }
    public String getNazwaDruzyny() { return nazwaDruzyny; }
    public int getMeczeRozegrane() { return meczeRozegrane; }
    public int getWygrane() { return wygrane; }
    public int getPrzegrane() { return przegrane; }
    public int getRemisy() { return remisy; }
    public int getPunkty() { return punkty; }
}
