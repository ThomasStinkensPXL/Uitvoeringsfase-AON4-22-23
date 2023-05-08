package example.micronaut.commands;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class DierSaveRequest {
    private String ras, naam, geslacht;

    public DierSaveRequest(String ras, String naam, String geslacht) {
        this.ras = ras;
        this.naam = naam;
        this.geslacht = geslacht;
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
}
