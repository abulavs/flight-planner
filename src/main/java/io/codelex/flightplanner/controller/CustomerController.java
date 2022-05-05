package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.Airport;
import io.codelex.flightplanner.Flight;
import io.codelex.flightplanner.PageResult;
import io.codelex.flightplanner.SearchFlightRequest;
import io.codelex.flightplanner.service.FlightsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final FlightsService flightsService;

    public CustomerController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @GetMapping("/flights/{id}")
    public Flight findFlight(@PathVariable("id") Long id) {
        return flightsService.findById(id);
    }

    @GetMapping("/airports")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Airport> searchAirport(@RequestParam("search") String search) {
        return flightsService.searchAirport(search);
    }

    @PostMapping("flights/search")
    public PageResult<Flight> searchFlight(@RequestBody @Valid SearchFlightRequest searchFlightRequest) {
        return flightsService.searchFlight(searchFlightRequest);
    }

}
