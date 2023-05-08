package org.dailycodebuffer.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Schuur extends PanacheEntity {
    @OneToMany
    private List<Dier> dieren;
    private String naamSchuur;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long schuurId;

    public Schuur(String naamSchuur) {
        this.dieren = new ArrayList<>();
        this.naamSchuur = naamSchuur;
    }

    public Schuur() {
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

//    public Long getSchuurId() {
//        return schuurId;
//    }
//
//    public void setSchuurId(Long schuurId) {
//        this.schuurId = schuurId;
//    }
}
