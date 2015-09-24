package com.southwest.southwestapp.controllers;

import com.southwest.southwestapp.models.CheckIn;
import com.southwest.southwestapp.models.Passenger;

import java.util.ArrayList;

/**
 * Created by luisalfonsobejaranosanchez on 9/17/15.
 */
public class CheckInController {

    private CheckIn checkIn;

    public CheckInController() {
        checkIn = new CheckIn();
    }

    public Passenger getPassenger(int index) {
        return checkIn.getPassengers().get(index);
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        checkIn.setPassengers(passengers);
    }

    public void setConfirmationNumber(String confirmationNumber) {
        checkIn.setConfirmationNumber(confirmationNumber);
    }

    public CheckIn getCheckIn() {
        return checkIn;
    }

}
