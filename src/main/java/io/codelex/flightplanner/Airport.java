package io.codelex.flightplanner;


import javax.validation.constraints.NotBlank;
import java.util.Objects;


public class Airport {
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String airport;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport1 = (Airport) o;
        return Objects.equals(airport.toLowerCase().trim(), airport1.airport.toLowerCase().trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(airport);
    }
}
