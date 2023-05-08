package com.example.casepaper.api.dto;

import java.time.LocalDate;

public record DierDTO(Long id, String ras, String naam, String geslacht, LocalDate geboorteDatum) {
}
