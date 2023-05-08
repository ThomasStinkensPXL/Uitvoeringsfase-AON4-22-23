package example.micronaut.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Serdeable
@Entity
@Table(name="schuren")
public class Schuur {
    @OneToMany(fetch = FetchType.EAGER)
    private List<Dier> dieren;
    private String naamSchuur;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schuurId;

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

    public Long getSchuurId() {
        return schuurId;
    }

    public void setSchuurId(Long schuurId) {
        this.schuurId = schuurId;
    }
}
