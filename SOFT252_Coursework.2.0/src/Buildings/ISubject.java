/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

/**
 *Produces interface ISubject/
 * Ensures registerObservers & notifyObservers method is available .
 */
public interface ISubject {

    public void registerObservers(IObservers observer);

    public void notifyObservers(String mode);

}
