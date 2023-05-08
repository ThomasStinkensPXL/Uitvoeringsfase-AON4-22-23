package org.dailycodebuffer.controllers;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dailycodebuffer.model.Dier;
import org.dailycodebuffer.model.Schuur;
import org.dailycodebuffer.requests.AddDierToSchuurRequest;
import org.dailycodebuffer.requests.SchuurRequest;
import org.dailycodebuffer.services.SchuurService;

import java.util.List;

@Path("/schuur")
public class SchuurController {
    @Inject
    SchuurService schuurService;

    @POST
    @Transactional
    @Path("/nieuw")
    public void maakSchuur(SchuurRequest schuurRequest) {
        schuurService.maakSchuur(schuurRequest);
    }

    @POST
    @Transactional
    @Path("/addDier")
    public void voegDierToe(AddDierToSchuurRequest addDierToSchuurRequest) {
        schuurService.addDier(addDierToSchuurRequest);
    }

    @GET
    public List<Schuur> verkrijgSchuren() {
        return schuurService.krijgSchuren();
    }

//    @DELETE
//    @Transactional
//    @Path("/verwijderDier/{schuurId}/{dierId}")
//    public void verwijderDierUitSchuur(int schuurId, int dierId) {
//        schuurService.verwijderDierUitSchuur(schuurId, dierId);
//
//    }

    @GET
    @Path("/{naamSchuur}/getDieren")
    public List<Dier> verkrijgDierenInSchuur(String naamSchuur){
        return schuurService.krijgDierenInschuur(naamSchuur);
    }
}
