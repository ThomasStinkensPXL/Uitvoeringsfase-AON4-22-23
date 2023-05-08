package example.micronaut.commands;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class DierUpdateCommand {
    private int dierId;
    private String nieuwGeslacht;

    public DierUpdateCommand(int dierId, String nieuwGeslacht) {
        this.dierId = dierId;
        this.nieuwGeslacht = nieuwGeslacht;
    }

    public int getDierId() {
        return dierId;
    }

    public void setDierId(int dierId) {
        this.dierId = dierId;
    }

    public String getNieuwGeslacht() {
        return nieuwGeslacht;
    }

    public void setNieuwGeslacht(String nieuwGeslacht) {
        this.nieuwGeslacht = nieuwGeslacht;
    }
}
