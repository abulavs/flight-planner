package io.codelex.flightplanner.service;

import io.codelex.flightplanner.Airport;
import io.codelex.flightplanner.Flight;
import io.codelex.flightplanner.PageResult;
import io.codelex.flightplanner.SearchFlightRequest;
import io.codelex.flightplanner.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightsService {
    private final FlightRepository flightRepository;

    public FlightsService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void clearFlights() {
        flightRepository.clearFlights();
    }

    public void addFlight(Flight flight) {
        flightRepository.addFlight(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteFlight(id);
    }

    public Flight findById(Long id) {
        return flightRepository.findById(id);
    }

    public Long getCount() {
        return flightRepository.getId();
    }

    public List<Airport> searchAirport(String search) {
        return flightRepository.searchAirport(search);
    }

    public PageResult<Flight> searchFlight(SearchFlightRequest searchFlightRequest) {
        return flightRepository.searchFlight(searchFlightRequest);
    }
}
