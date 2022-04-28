package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private Long id = 0L;

    public Long getId() {
        return id;
    }

    private List<Flight> flightsList = new ArrayList<>();

    public synchronized void addFlight(Flight flight) {
        if (checkIfFlightAlreadyExist(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (flight.getFrom().equals(flight.getTo()) || containsInvalidDates(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        flightsList.add(flight);
        id++;
    }

    public synchronized void deleteFlight(Long id) {
        flightsList.removeIf(flight -> flight.getId().equals(id));
    }

    public void clearFlights() {
        flightsList.clear();
    }

    public Flight findById(Long id) {
        for (Flight flight : flightsList) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public List<Airport> searchAirport(String search) {
        List<Airport> airports = new ArrayList<>();
        for (Flight flight : flightsList) {
            if (flight.getFrom().getAirport().toLowerCase().startsWith(search.trim().toLowerCase())
                    || flight.getFrom().getCity().toLowerCase().startsWith(search.trim().toLowerCase())
                    || flight.getFrom().getCountry().toLowerCase().startsWith(search.trim().toLowerCase())) {
                airports.add(flight.getFrom());
            }
        }
        return airports;
    }

    public PageResult<Flight> searchFlight(SearchFlightRequest searchFlightRequest) {
        List<Flight> searchList = new ArrayList<>();

        if (searchFlightRequest.getFrom().trim().equalsIgnoreCase(searchFlightRequest.getTo().trim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        for (Flight flight : flightsList) {
            LocalDate searchDepartureDate = LocalDate.parse(searchFlightRequest.getDepartureDate());

            if (flight.getFrom().getAirport().equalsIgnoreCase(searchFlightRequest.getFrom())
                    && flight.getTo().getAirport().equalsIgnoreCase(searchFlightRequest.getTo())
                    && flight.getDepartureTime().toLocalDate().equals(searchDepartureDate)) {
                searchList.add(flight);
            }
        }
        return new PageResult<>(0L, (long) searchList.size(), searchList);
    }


    private boolean checkIfFlightAlreadyExist(Flight flight) {
        for (Flight f : flightsList) {
            if (f.matches(flight)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsInvalidDates(Flight flight) {
        return flight.invalidDatesCheck(flight);
    }
}











