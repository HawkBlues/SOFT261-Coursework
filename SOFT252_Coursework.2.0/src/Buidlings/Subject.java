/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings;

/**
 *
 * @author Max
 */
public interface Subject {

    public void registerObservers(Observers observer);

    public void notifyObservers(String mode);

}
