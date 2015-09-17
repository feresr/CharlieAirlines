package com.southwest.southwestapp.controllers;

import com.southwest.southwestapp.vo.CheckInVO;
import com.southwest.southwestapp.vo.PassengerVO;

/**
 * Created by luisalfonsobejaranosanchez on 9/17/15.
 */
public class CheckInController {

    private CheckInVO checkInVO;

    public CheckInController() {
        checkInVO = new CheckInVO();
    }

    public PassengerVO getPassanger(int index) {
        return checkInVO.getPassengers()[index];
    }

    public void setPassangers(PassengerVO[] passangers) {
        checkInVO.setPassengers(passangers);
    }

    public void setConfirmationNumer(String confirmationNumber) {
        checkInVO.setConfirmationNumber(confirmationNumber);
    }

    public CheckInVO getCheckin() {
        return checkInVO;
    }

}
