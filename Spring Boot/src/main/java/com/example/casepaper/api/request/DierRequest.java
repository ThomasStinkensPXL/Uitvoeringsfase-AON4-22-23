package com.example.casepaper.api.request;

import java.time.LocalDate;

public record DierRequest(String ras, String naam, String geslacht, LocalDate geboorteDatum) {
}
