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
public class Visitor extends Person {
    
    public Visitor(Integer id, String name, List<String> role) {
        super(id, name, role);
    }
}