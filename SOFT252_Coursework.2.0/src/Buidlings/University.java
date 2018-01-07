/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings;

import java.util.List;

/**
 *
 * @author Max
 */
public class University implements IBuilding {

    private List<Campus> CampusList;
    private String UniversityName;
    private Integer UniversityId;
    
    
    public University(String bName, Integer bId) {
        
        this.UniversityName = bName;
        this.UniversityId = bId;
        
    }
    
    public void makeCampus(String name){
        this.CampusList.add(new Campus(name));
    }
    
    public void removeCampus(Integer index){
        this.CampusList.remove(index);
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
    
}
