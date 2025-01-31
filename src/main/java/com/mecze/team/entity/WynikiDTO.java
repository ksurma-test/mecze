package com.mecze.team.entity;

public class WynikiDTO {

    private String nazwaDruzyny;
    private int meczeRozegrane;
    private int wygrane;
    private int przegrane;
    private int remisy;
    private int punkty;

    public WynikiDTO(String nazwaDruzyny, int meczeRozegrane, int wygrane, int przegrane, int remisy, int punkty) {
        this.nazwaDruzyny = nazwaDruzyny;
        this.meczeRozegrane = meczeRozegrane;
        this.wygrane = wygrane;
        this.przegrane = przegrane;
        this.remisy = remisy;
        this.punkty = punkty;
    }


    public String getNazwaDruzyny() {
        return nazwaDruzyny;
    }

    public void setNazwaDruzyny(String nazwaDruzyny) {
        this.nazwaDruzyny = nazwaDruzyny;
    }

    public int getMeczeRozegrane() {
        return meczeRozegrane;
    }

    public void setMeczeRozegrane(int meczeRozegrane) {
        this.meczeRozegrane = meczeRozegrane;
    }

    public int getWygrane() {
        return wygrane;
    }

    public void setWygrane(int wygrane) {
        this.wygrane = wygrane;
    }

    public int getPrzegrane() {
        return przegrane;
    }

    public void setPrzegrane(int przegrane) {
        this.przegrane = przegrane;
    }

    public int getRemisy() {
        return remisy;
    }

    public void setRemisy(int remisy) {
        this.remisy = remisy;
    }

    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }
}
