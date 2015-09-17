package com.southwest.southwestapp.vo;

import java.util.List;

/**
 * Created by zadtanikus on 15/09/15.
 */
public class CheckInVO {

    private String flightNumber;
    private String travelTime;
    private String gate;
    private String confirmationNumber;
    private PassengerVO[] passengers;

    public CheckInVO() {
    }

    public CheckInVO(String flightNumber, String travelTime, String gate, String confirmationNumber, PassengerVO[] passengers) {
        this.flightNumber = flightNumber;
        this.travelTime = travelTime;
        this.gate = gate;
        this.confirmationNumber = confirmationNumber;
        this.passengers = passengers;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public PassengerVO[] getPassengers() {
        return passengers;
    }

    public void setPassengers(PassengerVO[] passengers) {
        this.passengers = passengers;
    }
}
