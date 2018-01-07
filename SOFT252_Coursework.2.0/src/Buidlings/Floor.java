/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings;

import java.util.ArrayList;
import java.util.List;

/**
 * Produces Floor class which instantiates Properties.
 *
 */
public class Floor implements ISubject, IObservers {

    private List<Room> RoomList;
    private Integer FloorNumber;
    private ArrayList<IObservers> Observers = new ArrayList<>();
    private Building building;

    /**
     * Floor constructor populates current floor object with floor number,
     * registars the observer and produces a room list array.
     *
     * @param floorNumber
     * @param building
     */
    public Floor(Integer floorNumber, Building building) {
        this.FloorNumber = floorNumber;
        this.RoomList = new ArrayList<>();
        building.registerObservers(this);
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }

    /**
     * Registers current object to observers list.
     *
     * @param observer
     */
    @Override
    public void registerObservers(IObservers observer) {
        this.Observers.add(observer);
    }

    /**
     * Iterates through entire observers list, updating the mode for each
     * object.
     *
     * @param mode
     */
    @Override
    public void notifyObservers(String mode) {
        Observers.stream().forEach((o) -> {
            o.update(mode);
        });
    }

    /**
     * Used to pass current mode to update observers. (Following style used in
     * other classes)
     *
     * @param mode
     */
    @Override
    public void update(String mode) {
        notifyObservers(mode);
    }

    /**
     * Adds new room to RoomList for current building object.
     *
     * @param name
     * @param type
     * @param building
     */
    public void makeRoom(String name, String type, Floor floor) {
        this.RoomList.add(new Room(name, type, floor));
    }

    /**
     * Removes room from current building Object.
     *
     * @param index
     */
    public void removeRoom(Integer index) {
        this.RoomList.remove(index);
    }

    /**
     * Returns list of rooms for current building object.
     *
     * @return
     */
    public List<Room> getRoomList() {
        return RoomList;
    }

    public Integer getFloorNumber() {
        return this.FloorNumber;
    }

    /**
     * Enables current building object roomList to be overwritten with new
     * building roomList.
     *
     * @param RoomList
     */
    public void setRoomList(List<Room> RoomList) {
        this.RoomList = RoomList;
    }

}
