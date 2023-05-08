package com.example.casepaper.service;

import com.example.casepaper.api.dto.DierDTO;
import com.example.casepaper.api.request.DierRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DierService {

    List<DierDTO> krijgAlleDieren();

    String verwijderDier(Long id);

    String pasGeslachtAan(Long id, DierRequest dierRequest );

    List<DierDTO> krijgDierVanSchuur(Long id);
}
