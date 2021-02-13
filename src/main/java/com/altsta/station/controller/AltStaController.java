package com.altsta.station.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AltStaController {

    @GetMapping("/alternate/stations/{station}")
    public String fetchStations(@PathVariable (name = "station") String station) {
        return station;
    }
}

