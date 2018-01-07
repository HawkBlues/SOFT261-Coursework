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
public class Campus implements IBuilding, Subject {

    private List<Building> BuildingList;
    private String CampusName;
    private String CampusMode;
    private ArrayList<Observers> Observers = new ArrayList<>();

    public Campus(String name) {
        this.CampusName = name;
        this.BuildingList = new ArrayList<>();
        this.CampusMode = "Normal";
    }

    public List<Building> getBuildingList() {
        return this.BuildingList;
    }

    public void makeBuilding(String Name, Campus campus) {
        this.BuildingList.add(new Building(Name, campus));
    }

    public void removeBuilding(Integer index) {
        this.BuildingList.remove(index);
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
    public void notifyObservers(String mode) {
        this.CampusMode = mode;

        Observers.stream().forEach((o) -> {
            o.update(mode);
        });
    }

    @Override
    public void registerObservers(Observers observer) {
        this.Observers.add(observer);
    }

}
