/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.util.List;

/**
 * Abstract class Person, used as Base class for all card holders. instantiates
 * Property Swipecard.
 */
public abstract class Person {

    private SwipeCard MySwipeCard;

    public Person(Integer id, String name, List<String> role) {

        this.MySwipeCard = new SwipeCard(id, name, role);

    }

    /**
     * Returns the persons swipecard object.
     *
     * @return SwipeCard Object.
     */
    public SwipeCard getMySwipeCard() {
        return this.MySwipeCard;
    }

    /**
     * Overwrites current swipecard object with new swipecard object.
     *
     * @param MySwipeCard
     */
    public void setMySwipeCard(SwipeCard MySwipeCard) {
        this.MySwipeCard = MySwipeCard;
    }

}
