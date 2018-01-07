/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.util.List;

/**
 *Produces swipecard object, populated with given parameters.
 * 
 */
public class SwipeCard {
    
    private Integer ID;
    private String Name;
    private List<String> Role;

    /**
     * Populates swipecard parameters with given parameters
     * @param id
     * @param name
     * @param role 
     */
    public SwipeCard(Integer id, String name, List<String> role) {
         this.ID = id;
         this.Name = name;
         this.Role = role;
    }
    /**
     * Returns current swipecard ID
     * @return 
     */
    public Integer getID() {
        return ID;
    }
    /**
     * Populates Swipecard ID to given parameter
     * @param ID 
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }
    /**
     * Returns current swipecard Name
     * @return 
     */
    public String getName() {
        return Name;
    }
    /**
     * Sets current swipecard object name to given parameter.
     * @param Name 
     */
    public void setName(String Name) {
        this.Name = Name;
    }
    /**
     * Returns current swipecard object role.
     * @return 
     */
    public List<String> getRole() {
        return Role;
    }

}
