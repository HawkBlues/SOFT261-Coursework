/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.io.Serializable;
import java.util.List;

/**
 *Creates Class EmergencyResponder and calls Super Class Constructor with given parameters.
 *
 */
public class EmergencyResponder extends Person implements Serializable{

    public EmergencyResponder(Integer id, String name, List<String> role) {
        super(id, name, role);
    }
}
