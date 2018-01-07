/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class Building implements IBuilding, Subject, Observers {

    private List<Room> RoomList;
    private String BuildingName;
    private String BuildingMode;
    private ArrayList<Observers> Observers = new ArrayList<>();

    public Building(String name, Campus campus) {
        this.BuildingName = name;
        this.BuildingMode = "Normal";
        this.RoomList = new ArrayList<>();
        campus.registerObservers(this);
    }

    public void makeRoom(String name, String type, Building building) {
        this.RoomList.add(new Room(name, type, building));
    }

    public void removeRoom(Integer index) {
        this.RoomList.remove(index);
    }

    public List<Room> getRoomList() {
        return RoomList;
    }

    public void setRoomList(List<Room> RoomList) {
        this.RoomList = RoomList;
    }

    public String getBuildingMode() {
        return BuildingMode;
    }

    public void setBuildingMode(String BuildingMode) {
        this.BuildingMode = BuildingMode;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer setID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(String mode) {
        setBuildingMode(mode);
        notifyObservers(mode);
    }

    @Override
    public void registerObservers(Observers observer) {
        this.Observers.add(observer);
    }

    @Override
    public void notifyObservers(String mode) {
        Observers.stream().forEach((o) -> {
            o.update(mode);
        });
    }

}
