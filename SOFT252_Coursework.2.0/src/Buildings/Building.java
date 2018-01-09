/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

import java.util.ArrayList;
import java.util.List;

/**
 * Produces Building class which instantiates Properties.
 *
 */
public class Building implements IBuilding, ISubject, IObservers {

    private List<Floor> FloorList;
    private String BuildingName;
    private String BuildingMode;
    private ArrayList<IObservers> Observers = new ArrayList<>();

    /**
     * Populates current object with provided paramaters and default details,
     * produces List for all floors within building. Registers Building to
     * observers list.
     *
     * @param name
     * @param campus
     */
    public Building(String name, Campus campus) {
        this.BuildingName = name;
        this.BuildingMode = "Normal";
        this.FloorList = new ArrayList<>();
        campus.registerObservers(this);
    }

    /**
     *
     */
    public void addFloorList(List<Floor> floors) {
        this.FloorList = floors;
    }

    /**
     * Populates FloorList list with given paramaters. (Producind a list of
     * floors within buildings)
     *
     * @param floorNumb
     * @param building
     */
    public void makeFloor(Integer floorNumb, Building building) {
        this.FloorList.add(new Floor(floorNumb, building));
    }

    /**
     * Remove floor from list, using given index.
     *
     * @param index
     */
    public void removeFloor(Integer index) {
        this.FloorList.remove(index);
    }

    public List<Floor> getFloorList() {
        return FloorList;
    }

    public void addFloor(Floor floor) {
        this.FloorList.add(floor);
    }

    public String getBuildingName() {
        return BuildingName;
    }

    /**
     * Returns current building mode for current building object.
     *
     * @return
     */
    public String getBuildingMode() {
        return BuildingMode;
    }

    /**
     * Sets building mode for current building object.
     *
     * @param BuildingMode
     */
    public void setBuildingMode(String BuildingMode) {
        this.BuildingMode = BuildingMode;
    }

    /**
     * Returns current building object name.
     *
     * @return
     */
    @Override
    public String getName() {
        return this.BuildingName;
    }

    /**
     * Sets building name to given parameter (Allows building name to be
     * changed)
     *
     * @param name
     * @return
     */
    @Override
    public void setName(String name) {
        this.BuildingName = name;
    }

    /**
     * Using given parameter, updates all building modes, thus all rooms by
     * extension.
     *
     * @param mode
     */
    @Override
    public void update(String mode) {
        setBuildingMode(mode);
        notifyObservers(mode);
    }

    /**
     * Adds current object to Observers list.
     *
     * @param observer
     */
    @Override
    public void registerObservers(IObservers observer) {
        this.Observers.add(observer);
    }

    /**
     * Changes mode from given parameter for every object within Observers list.
     *
     * @param mode
     */
    @Override
    public void notifyObservers(String mode) {
        Observers.stream().forEach((o) -> {
            o.update(mode);
        });
    }

}
