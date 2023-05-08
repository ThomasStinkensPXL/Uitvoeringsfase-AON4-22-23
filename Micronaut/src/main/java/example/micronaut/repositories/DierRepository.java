package example.micronaut.repositories;

import example.micronaut.SortingAndOrderArguments;
import example.micronaut.domain.Dier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface DierRepository {
    Optional<Dier> findById(long id);

    Dier save(@NotBlank String ras, String naam, String geslacht);

    Dier saveWithException(@NotBlank String ras, String naam, String geslacht);

    void deleteById(long id);

    List<Dier> findAll(@NotNull SortingAndOrderArguments args);

    int update(long id, @NotBlank String name);
}
