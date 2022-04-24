package io.codelex.flightplanner;


import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class SearchFlightRequest {
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private String departureDate;

    public SearchFlightRequest(@NotNull String from, @NotNull String to, @NotNull String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

}
