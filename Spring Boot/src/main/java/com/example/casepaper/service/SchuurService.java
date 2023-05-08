package com.example.casepaper.service;

import com.example.casepaper.api.request.DierRequest;
import com.example.casepaper.api.request.SchuurRequest;
import org.springframework.web.bind.annotation.PostMapping;

public interface SchuurService {

    String maakSchuurAan(SchuurRequest schuurRequest);

    String voegDierToe(DierRequest dierRequest, Long schuurId);
}
