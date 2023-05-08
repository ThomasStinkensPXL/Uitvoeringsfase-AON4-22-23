package example.micronaut.commands;

import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.NotBlank;

@Serdeable
public class SchuurSaveCommand {
    @NotBlank
    private String naamSchuur;

    public SchuurSaveCommand(String naamSchuur) {
        this.naamSchuur = naamSchuur;
    }

    public String getSchuurNaam() {
        return naamSchuur;
    }

    public void setSchuurNaam(String naamSchuur) {
        this.naamSchuur = naamSchuur;
    }
}
