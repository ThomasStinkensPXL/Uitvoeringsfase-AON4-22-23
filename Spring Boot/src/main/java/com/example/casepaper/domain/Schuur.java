package com.example.casepaper.domain;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Schuur")
public class Schuur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schuurId;
    private String naamSchuur;
    @OneToMany
    private List<Dier> dieren;


    public Schuur() {
    }

    public Schuur(List<Dier> dieren, String naamSchuur, long schuurId) {
        this.dieren = dieren;
        this.naamSchuur = naamSchuur;
        this.schuurId = schuurId;
    }

    public List<Dier> getDieren() {
        return dieren;
    }

    public void setDieren(List<Dier> dieren) {
        this.dieren = dieren;
    }

    public String getNaamSchuur() {
        return naamSchuur;
    }

    public void setNaamSchuur(String naamSchuur) {
        this.naamSchuur = naamSchuur;
    }

    public long getSchuurId() {
        return schuurId;
    }

    public void setSchuurId(long schuurId) {
        this.schuurId = schuurId;
    }
}
