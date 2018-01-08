/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings;

import accessPeople.ContractCleaner;
import accessPeople.Staff;
import accessPeople.Student;
import accessPeople.Visitor;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max
 */
public class RoomTest {

    private Campus campus;
    private Building building;
    private Floor floor;
    private Room room;
    private Student student;
    private Visitor visitor;
    private LocalTime localTime;
    private Staff staff;
    private ContractCleaner contractCleaner;

    public RoomTest() {

        this.campus = new Campus("Plymouth");

        this.building = new Building("Babbage", this.campus);

        this.floor = new Floor(3, this.building);

        this.room = new Room("BGB", "LectureHall", this.floor);

//        List<String> Role = new ArrayList();
//
//        Role.add("Student");
//
//        this.student = new Student(3232, "Joe", Role);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.localTime = LocalTime.now();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSetRoomMode() {
    }

    /**
     * Confirming no room is added before being explicitly told to do so.
     */
    @Test
    public void testIfRoomOnBuildingFalse() {
        // This should return false.
        if (this.building.getFloorList().size() == 1) {
            assertTrue(false);
        } else {
            assertTrue(true);
        }

    }

    /**
     * Confirming addFloor correctly adds 1 floor.
     */
    @Test
    public void TestIfRoomOnBuildingTrue() {

        this.building.addFloor(this.floor);

        // This should return true.
        if (this.building.getFloorList().size() == 1) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    /**
     * Confirming default room mode is normal.
     */
    @Test
    public void testGetRoomMode() {
        assertEquals(this.room.getRoomMode(), "Normal");
    }

    /**
     * Confirming Room type recall is correct.
     */
    @Test
    public void testGetRoomType() {
        assertEquals(this.room.getRoomType(), "LectureHall");
    }

    /**
     * Confirming Room name is correct.
     */
    @Test
    public void testGetRoomName() {
        assertEquals(this.room.getRoomName(), "BGB");
    }

    /**
     * Confirms that a Student can/cannot access Lecture Hall dependent on local
     * time.
     *
     * @throws Exception
     */
    @Test
    //put time in, then try with two different rooms.
    public void testTryRoomAccessStudent() throws Exception {

        List<String> Role = new ArrayList();

        Role.add("Student");

        this.student = new Student(3232, "Joe", Role);

        LocalTime EndTime;
        LocalTime StartTime;
        EndTime = LocalTime.of(22, 00);
        StartTime = LocalTime.of(8, 30);

        if (localTime.isAfter(EndTime) || localTime.isBefore(StartTime)) {
            assertFalse(this.room.tryRoomAccess(this.student.getMySwipeCard()));
        } else {
            assertTrue(this.room.tryRoomAccess(this.student.getMySwipeCard()));
        }

    }

    @Test
    public void testTryRoomAccessVisitor() throws Exception {

        List<String> Role = new ArrayList();

        Role.add("Visitor");

        this.visitor = new Visitor(333, "Max", Role);

        LocalTime EndTime;
        LocalTime StartTime;
        EndTime = LocalTime.of(22, 00);
        StartTime = LocalTime.of(8, 30);

        if (localTime.isAfter(EndTime) || localTime.isBefore(StartTime)) {
            assertFalse(this.room.tryRoomAccess(this.visitor.getMySwipeCard()));
        } else {
            assertTrue(this.room.tryRoomAccess(this.visitor.getMySwipeCard()));
        }
    }

    @Test
    public void testTryRoomAccessStaff() throws Exception {

        List<String> Role = new ArrayList();

        Role.add("Staff");

        this.staff = new Staff(333, "Max", Role);

        LocalTime EndTime;
        LocalTime StartTime;
        EndTime = LocalTime.of(23, 59, 59);
        StartTime = LocalTime.of(8, 30);

        if (localTime.isAfter(EndTime) || localTime.isBefore(StartTime)) {
            assertFalse(this.room.tryRoomAccess(this.staff.getMySwipeCard()));
        } else {
            assertTrue(this.room.tryRoomAccess(this.staff.getMySwipeCard()));
        }

    }

    @Test
    public void testTryRoomAccessContractCleaner() throws Exception {

        List<String> Role = new ArrayList();

        Role.add("ContractCleaner");

        this.contractCleaner = new ContractCleaner(333, "Max", Role);

        LocalTime EndTime;
        LocalTime StartTime;
        EndTime = LocalTime.of(10, 30);
        StartTime = LocalTime.of(5, 30);

        if (localTime.isAfter(EndTime) || localTime.isBefore(StartTime)) {
            assertFalse(this.room.tryRoomAccess(this.contractCleaner.getMySwipeCard()));//Returning True?

        }

        EndTime = LocalTime.of(22, 30);
        StartTime = LocalTime.of(17, 30);

        if (localTime.isAfter(EndTime) || localTime.isBefore(StartTime)) {
            assertFalse(this.room.tryRoomAccess(this.contractCleaner.getMySwipeCard()));//Also returning true...

        } else {

            assertTrue(this.room.tryRoomAccess(this.contractCleaner.getMySwipeCard()));
        }

    }

    @Test
    public void testUpdate() {
    }

}
