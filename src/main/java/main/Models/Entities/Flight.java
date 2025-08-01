/*
package main.Models.Entities;
import java.util.Objects;

public class Flight {
    private int id;
    private String flightNumber;
    private Route route;
    private Aircraft aircraft;
    private double averageMark;  // Для хранения рейтинга (если используется в Condorcet)
    private int totalPassengers; // Дополнительное поле для клиента

    public Flight() {
        // Пустой конструктор для Gson
    }

    public Flight(int id, String flightNumber, Route route, Aircraft aircraft) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.route = route;
        this.aircraft = aircraft;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public int getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(int totalPassengers) {
        this.totalPassengers = totalPassengers;
    }

    // Методы для удобной работы
   */
/* public String getRouteDescription() {
        if (route != null) {
            return route.getDeparturePoint() + " → " + route.getArrivalPoint();
        }
        return "Маршрут не указан";
    }*//*


   */
/* public String getAircraftModel() {
        return aircraft != null ? aircraft.getModel() : "Не указано";
    }*//*


    // equals, hashCode и toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    */
/*@Override
    public String toString() {
        return String.format("Рейс %s [%s], %s, самолет: %s",
                flightNumber,
                getRouteDescription(),
                aircraft != null ? "вместимость: " *//*
*/
/*+ aircraft.getCapacity()*//*
*/
/* : "",
                getAircraftModel());
    }*//*

}*/
