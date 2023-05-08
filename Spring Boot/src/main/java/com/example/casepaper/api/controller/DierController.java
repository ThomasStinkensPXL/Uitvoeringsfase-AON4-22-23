package com.example.casepaper.api.controller;

import com.example.casepaper.api.dto.DierDTO;
import com.example.casepaper.api.request.DierRequest;
import com.example.casepaper.exception.DierException;
import com.example.casepaper.service.DierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dieren")
public class DierController {

    private final DierService dierService;

    public DierController(DierService dierService) {
        this.dierService = dierService;
    }

    @GetMapping
    public Iterable<DierDTO> krijgAlleDieren(){
        return dierService.krijgAlleDieren();
    }

    @DeleteMapping("/verwijderDier/{id}")
    public ResponseEntity<String> verwijderDier(@PathVariable Long id){
        String response = dierService.verwijderDier(id);

        try {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (DierException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Er is een probleem bij het verwijderen van het dier.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bewerkDier/{id}")
    public ResponseEntity<String> bewerkDier(@RequestBody DierRequest dierRequest, @PathVariable Long id){
        String response = dierService.pasGeslachtAan(id , dierRequest);

        try {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (DierException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Er is een probleem bij het veranderen van het geslacht.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dierenVanSchuur/{id}")
    public Iterable<DierDTO> krijgAlleDierenVanSchuur(@PathVariable Long id){
        return dierService.krijgDierVanSchuur(id);
    }
}
