package org.dailycodebuffer.controllers;

import com.sun.tools.jconsole.JConsoleContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import org.dailycodebuffer.model.Dier;
import org.dailycodebuffer.requests.UpdateDierGeslachtRequest;
import org.dailycodebuffer.services.DierService;

import java.util.List;

@Path("/dier")
public class DierController {
    @Inject
    DierService dierService;

    @DELETE
    @Transactional
    @Path("/verwijder/{dierId}")
    public void verwijderDier(long dierId) {
        System.out.println(dierId);
        dierService.verwijderDier(dierId);
    }

    @GET
    public List<Dier> getDieren() {
        return dierService.getAllDieren();
    }

    @PUT
    @Transactional
    public void updateDierGeslacht(UpdateDierGeslachtRequest dierGeslachtRequest) {
        dierService.updateGeslachtVanDier(dierGeslachtRequest);
    }
}
