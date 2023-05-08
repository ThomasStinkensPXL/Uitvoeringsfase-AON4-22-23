package example.micronaut.commands;

import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.NotBlank;

@Serdeable
public class SchuurAddDierCommand {;

    @NotBlank
    private String naamSchuur;
    private DierSaveRequest dierSaveRequest;

    public SchuurAddDierCommand(String naamSchuur, DierSaveRequest dierSaveRequest) {
        this.naamSchuur = naamSchuur;
        this.dierSaveRequest = dierSaveRequest;
    }

    public String getNaamSchuur() {
        return naamSchuur;
    }

    public void setNaamSchuur(String naamSchuur) {
        this.naamSchuur = naamSchuur;
    }

    public DierSaveRequest getDierSaveRequest() {
        return dierSaveRequest;
    }

    public void setDierSaveRequest(DierSaveRequest dierSaveRequest) {
        this.dierSaveRequest = dierSaveRequest;
    }
}
