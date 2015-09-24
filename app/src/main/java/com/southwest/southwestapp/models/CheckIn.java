package com.southwest.southwestapp.models;

import java.util.ArrayList;
import java.util.List;

public class CheckIn {

    String confirmationNumber;
    String arrivesCity;
    String arrivesTime;
    String departsCity;
    String departsTime;
    String flightNumber;
    String gate;
    String city;
    String dateDay;
    String monthDate;
    String travelTime;
    List<Passenger> passengers = new ArrayList<Passenger>();

    public CheckIn() {
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getArrivesCity() {
        return arrivesCity;
    }

    public void setArrivesCity(String arrivesCity) {
        this.arrivesCity = arrivesCity;
    }

    public String getArrivesTime() {
        return arrivesTime;
    }

    public void setArrivesTime(String arrivesTime) {
        this.arrivesTime = arrivesTime;
    }

    public String getDepartsCity() {
        return departsCity;
    }

    public void setDepartsCity(String departsCity) {
        this.departsCity = departsCity;
    }

    public String getDepartsTime() {
        return departsTime;
    }

    public void setDepartsTime(String departsTime) {
        this.departsTime = departsTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateDay() {
        return dateDay;
    }

    public void setDateDay(String dateDay) {
        this.dateDay = dateDay;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger) {
        this.passengers.remove(passenger);
    }

}
