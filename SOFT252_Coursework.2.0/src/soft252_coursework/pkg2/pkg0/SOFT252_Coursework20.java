/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soft252_coursework.pkg2.pkg0;

import Buildings.Building;
import Buildings.Campus;
import Buidlings.Logging.Logger;
import Buildings.Room;
import Buildings.RoomAccessTime;
import accessPeople.PeopleFactory;
import accessPeople.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import static java.time.temporal.TemporalQueries.localTime;

/**
 *
 *
 */
public class SOFT252_Coursework20 {

    private PeopleFactory personFactory;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        List allStaffList = new ArrayList();
        List staffRole = new ArrayList();
        PeopleFactory newPerson = new PeopleFactory();

        staffRole.add("Staff");
        staffRole.add("Visitor");
        staffRole.add("Student");

        for (Integer i = 0; i < 100; i++) {
            allStaffList.add(newPerson.createPerson(i, "John", staffRole));
        }
        Person P1 = (Person) allStaffList.get(1);
        System.out.println(P1);

        RoomAccessTime timer = new RoomAccessTime();

        LocalTime before;
        LocalTime after;
        before = LocalTime.of(8, 30);
        after = LocalTime.of(22, 00);

        if (!timer.CheckTimeBetweenRange(before, after)) {
            System.out.println("YAY");
        }

        System.out.println("test");

        Campus c1 = new Campus("Plymouth");

        c1.makeBuilding("Babbage", c1);

        List<Building> buildings = c1.getBuildingList();

        Building b1 = buildings.get(0);

        c1.notifyObservers("false");

        System.out.println("End");

        Logger _logger = Logger.getInstance();

        try {
            _logger.WriteToAccessLogFile("Teswwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwt");
        } catch (IOException e) {
            System.out.println(e);
        }

//        Person newperson = new Person(2, "John");
//        System.out.println(newperson.getName());
    }

   

}
