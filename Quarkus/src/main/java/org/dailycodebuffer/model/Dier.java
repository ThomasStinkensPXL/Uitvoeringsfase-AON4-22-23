package org.dailycodebuffer.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Dier extends PanacheEntity {
    private String ras;
    private String naam;
    private String geslacht;
    private LocalDate geboorteDatum;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long dierId;

    public Dier(String ras, String naam, String geslacht) {
        this.ras = ras;
        this.naam = naam;
        this.geslacht = geslacht;
        this.geboorteDatum = LocalDate.now();
    }

    public Dier() {
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

//    public Long getDierId() {
//        return dierId;
//    }
//
//    public void setDierId(Long dierId) {
//        this.dierId = dierId;
//    }
}
