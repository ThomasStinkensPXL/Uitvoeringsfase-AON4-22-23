package org.dailycodebuffer.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.dailycodebuffer.model.Dier;
import org.dailycodebuffer.model.Schuur;
import org.dailycodebuffer.requests.UpdateDierGeslachtRequest;

import java.util.List;

@ApplicationScoped
public class DierService {
    public void verwijderDier(long dierId) {
        List<Schuur> schuren = Schuur.listAll();
        for (Schuur schuur : schuren) {
            schuur.getDieren().removeIf((dier -> dier.id == dierId));
        }

        Dier.delete("id", dierId);
    }

    public List<Dier> getAllDieren() {
        return Dier.listAll();
    }

    public void updateGeslachtVanDier(UpdateDierGeslachtRequest geslachtRequest) {
//        Dier dier = Dier.findById(geslachtRequest.idDier());
//        dier.setGeslacht(geslachtRequest.nieuwGeslacht());
//        System.out.println("-------------------------------------");
//        System.out.println(dier);
//        System.out.println(dier.getGeslacht());
//        System.out.println("-------------------------------------");

        // ?1 is een placeholder voor de 1ste value na de komma
        Dier.update("geslacht='" + geslachtRequest.nieuwGeslacht() + "' where id= ?1", geslachtRequest.idDier());
    }
}
