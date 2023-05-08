package org.dailycodebuffer.services;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.dailycodebuffer.model.Dier;
import org.dailycodebuffer.model.Schuur;
import org.dailycodebuffer.requests.AddDierToSchuurRequest;
import org.dailycodebuffer.requests.DierRequest;
import org.dailycodebuffer.requests.SchuurRequest;

import java.util.List;

@ApplicationScoped
public class SchuurService {
    public void maakSchuur(SchuurRequest schuurRequest) {
        Schuur schuur = new Schuur(schuurRequest.naam());
//        System.out.println(schuur.id);
        Schuur.persist(schuur);
//        Cool ding, de id wordt in het schuurobject geplaatst wanneer we deze persisten! we kunnen dus in volgende print het id effectief zien.
//        System.out.println(schuur.id);
    }

    public void addDier(AddDierToSchuurRequest addDierToSchuurRequest) {
        Schuur schuur = Schuur.find("naamSchuur", addDierToSchuurRequest.naamSchuur()).firstResult();

        if (schuur != null) {
            DierRequest dierRequest = addDierToSchuurRequest.dierRequest();
            if (dierRequest.naam() != null && dierRequest.ras() != null && dierRequest.geslacht() != null) {
                Dier nieuwDier = new Dier(dierRequest.ras(), dierRequest.naam(), dierRequest.geslacht());
                Dier.persist(nieuwDier);

                schuur.getDieren().add(nieuwDier);
            }
        }
    }

    public List<Schuur> krijgSchuren() {
        return Schuur.listAll();
    }

    public List<Dier> krijgDierenInschuur(String naamSchuur) {
        Schuur schuur = Schuur.find("naamSchuur", naamSchuur).firstResult();
        return schuur.getDieren();
    }
}
