package com.nixbyte.project.model.userpojo;

public class User {
    public int id;
    public String name;
    public String surname;
    public String email;
    public String password;

    public User(int id, String name, String surname, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
