package com.southwest.southwestapp.models;

/**
 * Created by zadtanikus on 15/09/15.
 */
public class Passenger {

    private String firstName;
    private String lastName;
    private String group;
    private int position;

    public Passenger() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
