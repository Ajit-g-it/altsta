package com.example.altsta.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class AltStaController {

    @GetMapping("/alternate/station/{station}")
    public List<String> getStation(@PathVariable(name = "station") String station) {
        return Arrays.asList("BHM", "RDU", "CLT");
    }

    @GetMapping(path = "/heathCheck")
    public ResponseEntity heathCheck() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
