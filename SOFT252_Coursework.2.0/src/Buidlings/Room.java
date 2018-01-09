/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings;

import Buidlings.Logging.AccessLogger;
import Buidlings.Logging.Logger;
import accessPeople.SwipeCard;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

/**
 * Creates new Class called Room, instantiating required Properties.
 *
 */
public class Room implements IObservers {

    private List<Room> RoomList;
    private String RoomName;
    private String RoomType;
    private String RoomMode;
    private RoomAccessTime RoomTime;
    private AccessLogger logger;
    private Floor floor;

    /**
     * Populate room object with name, type with default mode of "Normal" and
     * register the room object to Observers.
     *
     * @param name
     * @param type
     * @param floor
     */
    public Room(String name, String type, Floor floor) {
        this.RoomName = name;
        this.RoomType = type;
        this.RoomMode = "Normal";
        this.RoomTime = new RoomAccessTime();
        floor.registerObservers(this);
        this.floor = floor;
        this.logger = new AccessLogger();
    }

    /**
     * Set the object room mode to a new mode
     *
     * @param mode
     */
    public void setRoomMode(String mode) {
        this.RoomMode = mode;
    }

    /**
     * Return current object room Mode.
     *
     * @return
     */
    public String getRoomMode() {
        return this.RoomMode;
    }

    public Floor getFloor() {
        return floor;
    }
    
    /**
     * Returns room type from current object.
     *
     * @return
     */
    public String getRoomType() {
        return this.RoomType;
    }

    /**
     * Returns Room name from current object
     *
     * @return
     */
    public String getRoomName() {
        return this.RoomName;
    }

    /**
     * Returns Boolean if card can access current room (Allowing multiple Role
     * types)
     *
     * @param card
     * @return
     */
    public Boolean tryRoomAccess(SwipeCard card) throws IOException {

        List<String> roles = card.getRole();

        for (Integer i = 0; i < card.getRole().size(); i++) {
            // If asses roles taking in the current role returns true access was found.
            if (this.assessRoles(roles.get(i))) {
                this.logger.createAccessLog(card, this.floor.getFloorNumber(), this.floor.getBuilding().getName(), this.RoomName, true);
                return true; 
            }
        }
        return false;
    }
    

    /**
     * Given a Role, assess if Card has access to a room.
     *
     * @return
     */
    private Boolean assessRoles(String role) {

        LocalTime start;
        LocalTime end;

        switch (role) {
            case "Visitor":

                if (this.RoomMode != "Normal") {
                    return false;
                }
                if (this.RoomType != "LectureHall") {
                    return false;
                }

                start = LocalTime.of(8, 30);
                end = LocalTime.of(22, 00);

                if (!this.RoomTime.CheckTimeBetweenRange(start, end)) {
                    return false; //if not within time ranges, false.
                }
                return true; //Only time it could be true  

            case "Staff":
                if (this.RoomMode != "Normal") {
                    return false;
                }
                if (this.RoomType == "LectureHall" || this.RoomType == "StudentLab" || this.RoomType == "ResearchLab" || this.RoomType == "StaffRoom") {
                    return true;
                }

                start = LocalTime.of(5, 30);
                end = LocalTime.of(22, 59, 59);

                if (this.RoomTime.CheckTimeBetweenRange(start, end)) {
                    return true; //if not within time ranges, true.
                }

                return false;

            case "Student":
                Boolean roomOk = false;
                if (this.RoomMode != "Normal") {
                    
                    return false;
                }
                if (this.RoomType == "LectureHall" || this.RoomType == "StudentLab") {
                    
                    roomOk = true;
                    return true;
                }

                start = LocalTime.of(8, 30);
                end = LocalTime.of(22, 00);

                if (this.RoomTime.CheckTimeBetweenRange(start, end) && roomOk == true) {
                    return true; //if not within time ranges, true.
                }

                return false;

            case "ContractCleaner":
                Boolean roomOk1 = false;
                if (this.RoomMode != "Normal") {
                    return false;
                }
                if (this.RoomType != "SecureRoom") {
                    roomOk1 = true;
                    return true;
                }

                start = LocalTime.of(5, 30);
                end = LocalTime.of(10, 30);

                if (this.RoomTime.CheckTimeBetweenRange(start, end) && roomOk1 == true) {
                    return true; //if not within time ranges, true.
                }

                start = LocalTime.of(17, 30);
                end = LocalTime.of(22, 30);

                if (this.RoomTime.CheckTimeBetweenRange(start, end) && roomOk1 == true) {
                    return true; //if not within time ranges, true.
                }

                return false;

            case "Manager":
                if (this.RoomMode != "Normal") {
                    return false;
                }
                return true;

            case "Security":
                return true;

            case "EmergencyResponder":
                if (this.RoomMode != "Normal") {
                    return true;
                }

        }
        return false;

    }

    /**
     * Sets current room mode to given parameter
     *
     * @param mode
     */
    @Override
    public void update(String mode) {
        setRoomMode(mode);
    }

}
