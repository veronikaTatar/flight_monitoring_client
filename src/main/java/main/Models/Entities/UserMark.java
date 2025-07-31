package main.Models.Entities;

import java.util.Objects;

public class UserMark implements Comparable<UserMark> {
    private int id;
    private int mark;
    private Flight flight;
    private User user;

    public UserMark() {
    }

    public UserMark(int id, int mark, Flight flight, User user) {
        this.id = id;
        this.mark = mark;
        this.flight = flight;
        this.user = user;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int compareTo(UserMark o) {
        return Integer.compare(this.mark, o.mark);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMark userMark = (UserMark) o;
        return id == userMark.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserMark{" +
                "id=" + id +
                ", mark=" + mark +
                ", flightId=" + (flight != null ? flight.getId() : "null") +
                ", userId=" + (user != null ? user.getId() : "null") +
                '}';
    }
}