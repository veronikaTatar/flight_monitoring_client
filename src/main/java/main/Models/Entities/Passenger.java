package main.Models.Entities;
import java.util.Objects;
public class Passenger {
    private int id;
    private int placeNumber;
    private Flight flight;
    private PersonData personData;

    public Passenger() {
    }

    public Passenger(int id, int placeNumber, Flight flight, PersonData personData) {
        this.id = id;
        this.placeNumber = placeNumber;
        this.flight = flight;
        this.personData = personData;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", placeNumber=" + placeNumber +
                ", flightId=" + (flight != null ? flight.getId() : "null") +
                ", personDataId=" + (personData != null ? personData.getId() : "null") +
                '}';
    }
}