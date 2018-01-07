/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates Campus Class and Initiates Required Properties.
 *
 */
public class Campus implements IBuilding, ISubject {

    private List<Building> BuildingList;
    private String CampusName;
    private String CampusMode;
    private ArrayList<IObservers> Observers = new ArrayList<>(); 

    /**
     * Campus Constructor Class populates Campus parameters with given parameter
     * name, new Array list for building and sets default campus mode.
     *
     * @param name
     */
    public Campus(String name) {
        this.CampusName = name;
        this.BuildingList = new ArrayList<>();
        this.CampusMode = "Normal";
    }

    /**
     * Returns list of all building within current campus object.
     *
     * @return
     */
    public List<Building> getBuildingList() {
        return this.BuildingList;
    }

    /**
     * Adds new building object, with building Name and campus object and adds
     * that to Building list.
     *
     * @param Name
     * @param campus
     */
    public void makeBuilding(String Name, Campus campus) {
        this.BuildingList.add(new Building(Name, campus));
    }

    /**
     * Removes building object from building list, depending on index given from
     * interface.
     *
     * @param index
     */
    public void removeBuilding(Integer index) {
        this.BuildingList.remove(index);
    }

    /**
     * Returns current Campus object Name
     *
     * @return
     */
    @Override
    public String getName() {
        return this.CampusName;
    }

    /**
     * Sets current campus object name to given parameter
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.CampusName = name;
    }

    /**
     * Updates all campus' modes to given parameter, iterating though the entire
     * list.
     *
     * @param mode
     */
    @Override
    public void notifyObservers(String mode) {
        this.CampusMode = mode;

        Observers.stream().forEach((o) -> {
            o.update(mode);
        });
    }

    /**
     * Adds observer object to observer list.
     *
     * @param observer
     */
    @Override
    public void registerObservers(IObservers observer) {
        this.Observers.add(observer);
    }

}
