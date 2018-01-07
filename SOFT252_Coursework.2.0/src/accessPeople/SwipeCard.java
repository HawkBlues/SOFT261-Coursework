/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.util.List;

/**
 *
 * @author Max
 */
public class SwipeCard {

    private Integer ID;
    private String Name;
    private List<String> Role;

    public SwipeCard(Integer id, String name, List<String> role) {
         this.ID = id;
         this.Name = name;
         this.Role = role;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public List<String> getRole() {
        return Role;
    }

}
