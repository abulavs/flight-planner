package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.service.FlightsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingController {

    private final FlightsService flightsService;

    public TestingController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @RequestMapping("/testing-api/clear")
    @ResponseStatus(value = HttpStatus.OK)
    public void clearFlights() {
        flightsService.clearFlights();
    }

}
