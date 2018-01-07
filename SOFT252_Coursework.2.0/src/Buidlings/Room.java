/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings;

import accessPeople.SwipeCard;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Max
 */
public class Room implements Observers {

    private List<Room> RoomList;
    private String RoomName;
    private String RoomType;
    private String RoomMode;
    private RoomAccessTime RoomTime;

    public Room(String name, String type, Building building) {
        this.RoomName = name;
        this.RoomType = type;
        this.RoomMode = "Normal";
        building.registerObservers(this);
    }

    public void setRoomMode(String mode) {
        this.RoomMode = mode;
    }

    public String getRoomMode() {
        return this.RoomMode;
    }

    public String getRoomType() {
        return this.RoomType;
    }

    public String getRoomName() {
        return this.RoomName;
    }

    public Boolean tryRoomAccess(SwipeCard card) {

        List<String> roles = card.getRole();

        for (Integer i = 0; i < card.getRole().size(); i++) {
            // If asses roles taking in the current role returns true access was found.
            if (this.assessRoles(roles.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given a role, assess if Card has access to a room.
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
                if (this.RoomType == "LectureHall" && this.RoomType == "StudentLab" && this.RoomType == "ResearchLab" && this.RoomType == "StaffRoom") {
                    return true;
                }

                start = LocalTime.of(5, 30);
                end = LocalTime.of(22, 59, 59);

                if (this.RoomTime.CheckTimeBetweenRange(start, end)) {
                    return true; //if not within time ranges, true.
                }

                return false;

            case "Student":
                if (this.RoomMode != "Normal") {
                    return false;
                }
                if (this.RoomType == "LectureHall" && this.RoomType == "StudentLab") {
                    return true;
                }

                start = LocalTime.of(8, 30);
                end = LocalTime.of(22, 00);

                if (this.RoomTime.CheckTimeBetweenRange(start, end)) {
                    return true; //if not within time ranges, true.
                }

                return false;

            case "ContractCleaner":
                if (this.RoomMode != "Normal") {
                    return false;
                }
                if (this.RoomType != "SecureRoom") {
                    return true;
                }

                start = LocalTime.of(5, 30);
                end = LocalTime.of(10, 30);

                if (this.RoomTime.CheckTimeBetweenRange(start, end)) {
                    return true; //if not within time ranges, true.
                }

                start = LocalTime.of(17, 30);
                end = LocalTime.of(22, 30);

                if (this.RoomTime.CheckTimeBetweenRange(start, end)) {
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

    @Override
    public void update(String mode) {
        setRoomMode(mode);
    }

}
