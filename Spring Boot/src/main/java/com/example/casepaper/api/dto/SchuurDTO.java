package com.example.casepaper.api.dto;

import com.example.casepaper.domain.Dier;

import java.util.List;

public record SchuurDTO(Long id, String naamschuur, List<Dier> dieren) {
}
