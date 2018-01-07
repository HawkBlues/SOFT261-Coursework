/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings;

import java.util.List;

/**
 * Creates class University.
 * (instantiating Properties List String and Integer)
 * 
 */
public class University implements IBuilding {

    private List<Campus> CampusList;
    private String UniversityName;
    private Integer UniversityId;

    /**
     * Populate Building Name &* Building ID.
     *
     * @param bName
     * @param bId
     */
    public University(String bName, Integer bId) {

        this.UniversityName = bName;
        this.UniversityId = bId;

    }

    /**
     * Make new campus, adding name to campus List
     *
     * @param name
     */
    public void makeCampus(String name) {
        this.CampusList.add(new Campus(name));
    }

    /**
     * Remove campus from campus list
     *
     * @param index
     */
    public void removeCampus(Integer index) {
        this.CampusList.remove(index);
    }

    /**
     * Returns Current University Object Name
     *
     * @return
     */
    @Override
    public String getName() {
        return this.UniversityName;
    }

    /**
     * Sets current University Object Name to given parameter.
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.UniversityName = name;
    }

}
