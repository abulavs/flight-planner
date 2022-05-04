package io.codelex.flightplanner;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Flight {
    private Long id;
    private Airport from;
    private Airport to;
    private String carrier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public Flight(Long id, Airport from, Airport to, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean matches(Flight flight) {
        if (from.equals(flight.getFrom())
                && to.equals(flight.getTo())
                && carrier.equals(flight.getCarrier())
                && departureTime.equals(flight.getDepartureTime())
                && arrivalTime.equals(flight.getArrivalTime())) {
            return true;
        }
        return false;
    }

    public boolean containsInvalidDates(Flight flight) {
        if (flight.getDepartureTime().equals(flight.getArrivalTime()) || (flight.getDepartureTime().isAfter(flight.getArrivalTime()))) {
            return true;
        }
        return false;
    }
}
