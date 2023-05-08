package com.example.casepaper.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Dier")
public class Dier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dierId;
    private String ras;
    private String naam;
    private String geslacht;
    private LocalDate geboorteDatum;

    public Dier() {
    }

    public Dier(String ras, String naam, String geslacht, LocalDate geboorteDatum, Long dierId) {
        this.ras = ras;
        this.naam = naam;
        this.geslacht = geslacht;
        this.geboorteDatum = geboorteDatum;
        this.dierId = dierId;
    }

    public String getRas() {
        return ras;
    }

    public void setRas(String ras) {
        this.ras = ras;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(LocalDate geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public Long getDierId() {
        return dierId;
    }

    public void setDierId(Long dierId) {
        this.dierId = dierId;
    }
}
