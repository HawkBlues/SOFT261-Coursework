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
public class PeopleFactory { //factory class

    public PeopleFactory() {
        
       
        
    }

    public Person createPerson(Integer id, String name, List<String>role) {
        Person person = null;
        String currentRole = role.get(0);//getting first instance of whats inside, as that's most important (Allows multiple roles)
        
        switch(currentRole){ 
            case "Staff": 
                person = new Staff(id, name, role); //put in a switch for all staff members
                break;
            case "Student":
                person = new Student(id, name, role);
                break;
            case "EmergencyResponder":
                person = new EmergencyResponder(id, name, role);
                break;
            case "Visitor":
                person = new Visitor(id, name, role);
                break;
            case "Manager":
                person  = new Manager( id, name, role);
                break;
            case "Security":
                person = new Security(id, name, role);
                break;
            case "ContractCleaner":
                person = new ContractCleaner(id, name, role);
                break;
        }
        return person;
    }

}
