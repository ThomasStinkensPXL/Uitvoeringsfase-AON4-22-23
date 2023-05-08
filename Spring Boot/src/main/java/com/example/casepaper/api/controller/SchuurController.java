package com.example.casepaper.api.controller;

import com.example.casepaper.api.request.DierRequest;
import com.example.casepaper.api.request.SchuurRequest;
import com.example.casepaper.exception.SchuurException;
import com.example.casepaper.service.SchuurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schuur")
public class SchuurController {

    private final SchuurService schuurService;

    public SchuurController(SchuurService schuurService) {
        this.schuurService = schuurService;
    }

    @PostMapping("/maakSchuur")
    public ResponseEntity<String> maakSchuur(@RequestBody SchuurRequest schuurRequest){
        try {
            return new ResponseEntity<>(schuurService.maakSchuurAan(schuurRequest) + "", HttpStatus.CREATED);
        }catch (SchuurException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/voegDierToe/{id}")
    public ResponseEntity<String> voegDierToe(@RequestBody DierRequest dierRequest,@PathVariable Long id){
        try {
            return new ResponseEntity<>(schuurService.voegDierToe(dierRequest, id) + "", HttpStatus.CREATED);
        }catch (SchuurException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
