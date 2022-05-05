package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.AddFlightRequest;
import io.codelex.flightplanner.Flight;
import io.codelex.flightplanner.service.FlightsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api")
public class AdminController {
    private final FlightsService flightsService;

    public AdminController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @PutMapping("/flights")
    @ResponseStatus(value = HttpStatus.CREATED)
    public synchronized Flight addFlight(@RequestBody @Valid AddFlightRequest addFlightRequest) {
        Flight flight = addFlightRequest.toFlight(flightsService.getCount());
        flightsService.addFlight(flight);
        return flight;
    }

    @GetMapping("/flights/{id}")
    public Flight findFlight(@PathVariable("id") Long id) {
        return flightsService.findById(id);
    }


    @DeleteMapping("/flights/{id}")
    public void deleteFlight(@PathVariable("id") Long id) {
        flightsService.deleteFlight(id);
    }

}

