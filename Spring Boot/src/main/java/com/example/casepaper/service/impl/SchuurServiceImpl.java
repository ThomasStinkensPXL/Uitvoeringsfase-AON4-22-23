package com.example.casepaper.service.impl;

import com.example.casepaper.api.request.DierRequest;
import com.example.casepaper.api.request.SchuurRequest;
import com.example.casepaper.domain.Dier;
import com.example.casepaper.domain.Schuur;
import com.example.casepaper.repository.DierRepository;
import com.example.casepaper.repository.SchuurRepository;
import com.example.casepaper.service.SchuurService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchuurServiceImpl implements SchuurService {

    private final SchuurRepository schuurRepository;
    private final DierRepository dierRepository;

    public SchuurServiceImpl(SchuurRepository schuurRepository, DierRepository dierRepository) {
        this.schuurRepository = schuurRepository;
        this.dierRepository = dierRepository;
    }

    @Override
    public String maakSchuurAan(SchuurRequest schuurRequest) {
        Schuur schuur = new Schuur();
        schuur.setNaamSchuur(schuurRequest.naamSchuur());
        schuurRepository.save(schuur);
        return "Schuur " + schuur.getNaamSchuur() + " is aangemaakt met id "+ schuur.getSchuurId();
    }

    @Override
    public String voegDierToe(DierRequest dierRequest, Long schuurId) {
        Optional<Schuur> optionalSchuur = schuurRepository.findById(schuurId);
        if (optionalSchuur.isPresent()) {
            Schuur schuur = optionalSchuur.get();
            Dier dier = new Dier();
            dier.setNaam(dierRequest.naam());
            dier.setRas(dierRequest.ras());
            dier.setGeslacht(dierRequest.geslacht());
            dier.setGeboorteDatum(dierRequest.geboorteDatum());
            dierRepository.save(dier);
            List<Dier> dieren = schuur.getDieren();
            if (dieren == null) {
                dieren = new ArrayList<>();
            }
            dieren.add(dier);
            schuur.setDieren(dieren);
            schuurRepository.save(schuur);
            return "Dier " + dier.getNaam() + " is toegevoegd aan Schuur " + schuur.getNaamSchuur() + " met id " + dier.getDierId();
        } else {
            return "Schuur niet gevonden";
        }
    }
}
