package example.micronaut.repositories;

import example.micronaut.SortingAndOrderArguments;
import example.micronaut.commands.DierSaveRequest;
import example.micronaut.domain.Dier;
import example.micronaut.domain.Schuur;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface SchuurRepository {
    Optional<Schuur> findById(long id);

    Schuur save(@NotBlank String name);
    void addDier(String naamSchuur, DierSaveRequest dierSaveRequest);
    List<Dier> getDieren(String naamSchuur);

    Schuur saveWithException(@NotBlank String name);

    void deleteById(long id);

    List<Schuur> findAll(@NotNull SortingAndOrderArguments args);

    int update(long id, @NotBlank String name);

    void deleteAnimalFromBarn(long dierId);
}
