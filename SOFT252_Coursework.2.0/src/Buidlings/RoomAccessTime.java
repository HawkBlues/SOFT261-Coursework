/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings;

import java.time.LocalTime;

/**
 *
 * @author Max
 */
public class RoomAccessTime {

    private LocalTime localtime;

    public RoomAccessTime() {
        this.localtime = LocalTime.now();
    }

    public Boolean CheckTimeBetweenRange(LocalTime startTime, LocalTime EndTime) {

        if (localtime.isAfter(EndTime) || localtime.isBefore(startTime)) { //checks to see if timerange is outside specified times.
            return false;
        }
        if (localtime.isAfter(startTime) && localtime.isBefore(EndTime)) { //Checks to see if timerange is inside specificed times.
            return true;
        }
        return false;
    }

}
