package example.micronaut.controllers;

import example.micronaut.SortingAndOrderArguments;
import example.micronaut.commands.DierUpdateCommand;
import example.micronaut.domain.Dier;
import example.micronaut.domain.Schuur;
import example.micronaut.repositories.DierRepository;
import example.micronaut.repositories.SchuurRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import javax.validation.Valid;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/dier")
public class DierController {
    private final DierRepository dierRepository;
    private final SchuurRepository schuurRepository;

    DierController(DierRepository dierRepository, SchuurRepository schuurRepository) {
        this.dierRepository = dierRepository;
        this.schuurRepository = schuurRepository;
    }

    @Get("{?args*}")
    public List<Dier> getDieren(@Valid SortingAndOrderArguments args) {

        return dierRepository.findAll(args);
    }

    @Delete("/verwijder/{dierId}")
    public void verwijderDier(long dierId) {
        schuurRepository.deleteAnimalFromBarn(dierId);
        dierRepository.deleteById(dierId);
    }

    @Put
    HttpResponse<?> update(@Body @Valid DierUpdateCommand command) {
        int numberOfEntitiesUpdated = dierRepository.update(command.getDierId(), command.getNieuwGeslacht());

        return HttpResponse
                .noContent();
    }
}
