/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings.Logging;


import accessPeople.SwipeCard;
import java.io.IOException;
import java.time.LocalTime;


/**
 * Creates AccessLogger Class. Used to write each room access attempt to a log file.
 *
 */
public class AccessLogger {

    private LocalTime DateTime;
    private Logger _Logger;

    public AccessLogger() {
        this._Logger = Logger.getInstance();
    }
    
    /**
     * CreateAccessLog concatenates all required values into a string and passes them to WriteToAccessLogFile within the Logger Class
     * @param card
     * @param floorName
     * @param buildingName
     * @param roomName
     * @param accessGranted
     * @throws IOException 
     */
    public void createAccessLog(SwipeCard card, Integer floorName, String buildingName, String roomName, Boolean accessGranted) throws IOException {
        String value = (card.getName() + " " + card.getID() + " " + floorName + " " + buildingName + " " + roomName + " " + accessGranted);
        this._Logger.WriteToAccessLogFile(value);

    }

}
