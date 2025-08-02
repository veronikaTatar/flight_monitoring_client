package main.Models.Entities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.Enums.Roles;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private Roles role;  // Изменено на enum тип
    private PersonData personData;

    public User() {
    }

    public User(int id, String name, String login, String password, Roles role, PersonData personData) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.personData = personData;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }


    // В класс User
    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public StringProperty loginProperty() {
        return new SimpleStringProperty(login);
    }

    public ObjectProperty<Roles> roleProperty() {
        return new SimpleObjectProperty<>(role);
    }
}