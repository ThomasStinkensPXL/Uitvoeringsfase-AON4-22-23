package example.micronaut.controllers;

import example.micronaut.repositories.DierRepository;
import example.micronaut.repositories.SchuurRepository;
import example.micronaut.SortingAndOrderArguments;
import example.micronaut.commands.SchuurAddDierCommand;
import example.micronaut.commands.SchuurSaveCommand;
import example.micronaut.domain.Dier;
import example.micronaut.domain.Schuur;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import javax.validation.Valid;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/schuur")
public class SchuurController {
    private final SchuurRepository schuurRepository;
    private final DierRepository dierRepository;

    SchuurController(SchuurRepository schuurRepository, DierRepository dierRepository) {
        this.schuurRepository = schuurRepository;
        this.dierRepository = dierRepository;
    }
//    SchuurController(SchuurRepository schuurRepository) {
//        this.schuurRepository = schuurRepository;
//    }

    @Post
    HttpResponse<Schuur> save(@Body @Valid SchuurSaveCommand cmd) {
        Schuur schuur = schuurRepository.save(cmd.getSchuurNaam());
        return HttpResponse
                .created(schuur);
    }

    @Post("/addDier")
    public void voegDierToe(SchuurAddDierCommand addDierToSchuurRequest) {
        schuurRepository.addDier(addDierToSchuurRequest.getNaamSchuur(), addDierToSchuurRequest.getDierSaveRequest());
    }

    @Get(value = "{?args*}")
    List<Schuur> getAllSchuren(@Valid SortingAndOrderArguments args) {
        return schuurRepository.findAll(args);
    }

    @Get("/{naamSchuur}/getDieren")
    public List<Dier> verkrijgDierenInSchuur(String naamSchuur){
        return schuurRepository.getDieren(naamSchuur);
    }
}
