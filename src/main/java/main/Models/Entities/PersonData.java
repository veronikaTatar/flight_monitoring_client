package main.Models.Entities;

import javafx.beans.property.*;
import java.util.HashSet;
import java.util.Set;

public class PersonData {
    private int id;
    private int age;
    private String mail;
    private String address;
    private String sex;
    private Set<User> users = new HashSet<>();
    private Set<Passenger> passengers = new HashSet<>();

    public PersonData() {
    }

    public PersonData(int id, int age, String mail, String address, String sex, Set<User> users, Set<Passenger> passengers) {
        this.id = id;
        this.age = age;
        this.mail = mail;
        this.address = address;
        this.sex = sex;
        this.users = users;
        this.passengers = passengers;
    }

    // Геттеры/сеттеры (стандартные)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // JavaFX Property-методы (оставлены с оригинальными именами для совместимости)
    public ObjectProperty<Integer> ageProperty() {
        return new SimpleIntegerProperty(age).asObject();
    }

    public StringProperty mailProperty() {
        return new SimpleStringProperty(mail);
    }

    public StringProperty addressProperty() {
        return new SimpleStringProperty(address);
    }

    public StringProperty sexProperty() {
        return new SimpleStringProperty(sex);
    }
}