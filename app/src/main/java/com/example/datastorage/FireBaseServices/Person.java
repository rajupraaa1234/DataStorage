package com.example.datastorage.FireBaseServices;

public class Person {
    String name;
    String lastname;
    String dob;

    public Person(String name, String lastname, String dob) {
        this.name = name;
        this.lastname = lastname;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
