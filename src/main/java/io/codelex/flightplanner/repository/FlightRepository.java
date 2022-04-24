package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class FlightRepository {
    private Long count = 0L;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    List<Flight> flightsList = new ArrayList<>();

    public synchronized void addFlight(Flight flight) {
        if (checkIfFlightAlreadyExist(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (flight.getFrom().equals(flight.getTo()) || checkStrangeDates(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        flightsList.add(flight);
        count++;
    }

    public synchronized void deleteFlight(Long id) {
        flightsList.removeIf(flight -> Objects.equals(flight.getId(), id));
    }

    public void clearFlights() {
        flightsList.clear();
    }

    public Flight findById(Long id) {
        for (Flight flight : flightsList) {
            if (Objects.equals(flight.getId(), id)) {
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
            String flightDate = String.valueOf(flight.getDepartureTime()).substring(0, 10);

            if (flight.getFrom().getAirport().equalsIgnoreCase(searchFlightRequest.getFrom())
                    && flight.getTo().getAirport().equalsIgnoreCase(searchFlightRequest.getTo())
                    && flightDate.equals(searchFlightRequest.getDepartureDate())) {
                searchList.add(flight);
            }
        }
        return new PageResult<>(0L, (long) searchList.size(), searchList);
    }


    private boolean checkIfFlightAlreadyExist(Flight flight) {
        for (Flight f : flightsList) {
            if (f.getFrom().equals(flight.getFrom())
                    && f.getTo().equals(flight.getTo())
                    && f.getCarrier().equals(flight.getCarrier())
                    && f.getDepartureTime().equals(flight.getDepartureTime())
                    && f.getArrivalTime().equals(flight.getArrivalTime())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkStrangeDates(Flight flight) {
        return flight.getDepartureTime().equals(flight.getArrivalTime()) || (flight.getDepartureTime().isAfter(flight.getArrivalTime()));
    }
}











