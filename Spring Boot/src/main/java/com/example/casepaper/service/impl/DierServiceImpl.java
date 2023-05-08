package com.example.casepaper.service.impl;

import com.example.casepaper.api.dto.DierDTO;
import com.example.casepaper.api.request.DierRequest;
import com.example.casepaper.domain.Dier;
import com.example.casepaper.domain.Schuur;
import com.example.casepaper.exception.DierException;
import com.example.casepaper.exception.SchuurException;
import com.example.casepaper.repository.DierRepository;
import com.example.casepaper.repository.SchuurRepository;
import com.example.casepaper.service.DierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DierServiceImpl implements DierService {

    private final DierRepository dierRepository;
    private final SchuurRepository schuurRepository;

    public DierServiceImpl(DierRepository dierRepository, SchuurRepository schuurRepository) {
        this.dierRepository = dierRepository;
        this.schuurRepository = schuurRepository;
    }

    @Override
    public List<DierDTO> krijgAlleDieren() {
        return dierRepository.findAll()
                .stream()
                .map(dier -> new DierDTO(dier.getDierId(), dier.getRas(), dier.getNaam(), dier.getGeslacht(), dier.getGeboorteDatum()))
                .collect(Collectors.toList());
    }

    @Override
    public String verwijderDier(Long id) {
        Optional<Dier> optionalDier = dierRepository.findById(id);
        if (optionalDier.isPresent()) {
            Dier dier = optionalDier.get();
            List<Schuur> schuren = schuurRepository.findAll();
            boolean isDeleted = false;
            for (Schuur schuur : schuren) {
                List<Dier> dieren = schuur.getDieren();
                if (dieren != null && dieren.contains(dier)) {
                    dieren.remove(dier);
                    schuur.setDieren(dieren);
                    schuurRepository.save(schuur);
                    isDeleted = true;
                }
            }
            if (isDeleted) {
                dierRepository.deleteById(id);
                return "Dier is verwijderd.";
            } else {
                throw new DierException("Dier is niet gevonden in een schuur.");
            }
        } else {
            throw new DierException("Dier is niet gevonden.");
        }
    }

    @Override
    public String pasGeslachtAan(Long id, DierRequest dierRequest) {
        Optional<Dier> optionalDier = dierRepository.findById(id);
        if (optionalDier.isPresent()) {
            Dier dier = optionalDier.get();
            dier.setNaam(dierRequest.naam());
            dier.setGeslacht(dierRequest.geslacht());
            dier.setRas(dierRequest.ras());
            dier.setGeboorteDatum(dierRequest.geboorteDatum());
            dierRepository.save(dier);
            return "Geslacht van Dier met ID " + id + " is succesvol aangepast.";
        } else {
            return "Dier met ID " + id + " is niet gevonden.";
        }
    }

    @Override
    public List<DierDTO> krijgDierVanSchuur(Long id) {
        List<Dier> dieren = schuurRepository.findById(id)
                .orElseThrow(() -> new SchuurException("Fout bij het ophalen van de dieren."))
                .getDieren();
        return dieren.stream()
                .map(dier -> new DierDTO(dier.getDierId(), dier.getRas(), dier.getNaam(), dier.getGeslacht(), dier.getGeboorteDatum()))
                .collect(Collectors.toList());
    }
}
