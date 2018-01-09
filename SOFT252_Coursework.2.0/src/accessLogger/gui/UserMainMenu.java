/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessLogger.gui;

import Buildings.Building;
import Buildings.Campus;
import Buildings.Floor;
import Buildings.Room;
import accessPeople.PeopleFactory;
import accessPeople.Person;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import soft252_coursework.pkg2.pkg0.SOFT252_Coursework20;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class UserMainMenu extends javax.swing.JFrame {

    private DefaultListModel listModelPeople;

    private DefaultComboBoxModel listModelCampus2;
    private DefaultListModel listModelBuilding;

    private DefaultComboBoxModel listModelBuilding2;
    private DefaultComboBoxModel listModelFloor;

    private DefaultComboBoxModel listModelRoom;

    private DefaultComboBoxModel testing;
    private List<Person> personList;
    private PeopleFactory personFactory;
    private List<Campus> campusList;
    private List<String> campusList3;
    private String buildingName = "Babbage";
    private Integer floorNumber = 0;

    private Integer SelectedCampus = 0;
    private Integer SelectedBuilding = 0;
    private Integer SelectedFloor = 0;
    private Integer SelectedRoom = 0;

    /**
     * Creates new form UserMainMenu
     */
    public UserMainMenu() {

        listModelRoom = new DefaultComboBoxModel();

        //  buildingName = "Babbage";
        List<Person> peopleList = new ArrayList<>();

        peopleList = createPersonTestData();

        listModelPeople = new DefaultListModel();

        for (Integer i = 0; i < peopleList.size(); i++) {
            listModelPeople.addElement(peopleList.get(i).getMySwipeCard().getName());//Current Campus user list on GUI is populated from listModelPeople.
        }

        listModelFloor = new DefaultComboBoxModel();

        populateSites();

        initComponents();
    }

    private void populateSites() {
        List<Campus> campusList = new ArrayList<>();
        this.campusList = populateCampus();

        System.out.println(this.campusList);

        List<Building> buildingList = new ArrayList<>();
        buildingList = populateBuilding(this.campusList);

        Campus c1 = this.campusList.get(0);

        c1.addBuildingList(buildingList);

        List<Building> buildings = c1.getBuildingList();

        List<Floor> floorList = new ArrayList<>();
        floorList = populateFloors(buildings);

        buildings.get(0).addFloorList(floorList);

        List<Room> testRooms = createRoomTestData(floorList);

        buildings.get(0).getFloorList().get(0).setRoomList(testRooms);

        System.out.println();

        listModelCampus2 = new DefaultComboBoxModel();
        listModelBuilding2 = new DefaultComboBoxModel();
        listModelFloor = new DefaultComboBoxModel();

        for (Integer i = 0; i < this.campusList.size(); i++) {
            listModelCampus2.addElement(this.campusList.get(i).getName());
        }

//        List<Building> buildingOutList = this.campusList.get(0).getBuildingList();
//
//        for (Integer v = 0; v < buildingOutList.size(); v++) {
//            listModelBuilding2.addElement(buildingOutList.get(v).getName());
//        }
    }

    private List<Campus> populateCampus() {

        List<Campus> campusList = new ArrayList<>();

        campusList = createCampusTestData();

        return campusList;
    }

    private List<Building> populateBuilding(List<Campus> campusList) {

        List<Building> buildingList = new ArrayList<>();
        buildingList = createBuildingTestData(campusList);

//        for (Integer i = 0; i< buildingList.size(); i++){
//        System.out.println("Testing");
//            if (buildingList.get(i).getBuildingName() == buildingName){
//                System.out.println("Yup");
//        jLabel3.setText(buildingList.get(i).getBuildingMode());
//            }
//        }
        //  jLabelCampusState.setText(buildingList.get(0).getBuildingMode());
        return buildingList;
    }

    private List<Floor> populateFloors(List<Building> buildingList) {

        List<Floor> floorList = new ArrayList<>();

        floorList = createFloorTestData(buildingList);

        try {

            for (Integer i = 0; i < floorList.size(); i++) {

                if (floorList.get(i).getBuilding().getName() == buildingName) {

                    listModelFloor.addElement(floorList.get(i).getFloorNumber());//Have default value of 0 (getting first building name), when user clicks another, get that index and update it.
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return floorList;
    }

    private void populateRooms(List<Floor> floorList) {

        List<Room> roomList = new ArrayList<>();

        roomList = createRoomTestData(floorList);

        try {

            for (Integer i = 0; i < roomList.size(); i++) {

                if (roomList.get(i).getFloor().getFloorNumber() == floorNumber && roomList.get(i).getFloor().getBuilding().getName() == buildingName) {//if the room is on the same floor in same building as selected.

                    listModelRoom.addElement(roomList.get(i).getRoomName());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private List<Person> createPersonTestData() { //Produces Test Data for GUI
        List<Person> peopleList = new ArrayList<>();

        personFactory = new PeopleFactory();

        List<String> role = new ArrayList<>();

        role.add("Staff");

        peopleList.add(personFactory.createPerson(11125, "John Smith", role));
        peopleList.add(personFactory.createPerson(21125, "Paul Smith", role));
        peopleList.add(personFactory.createPerson(31125, "Ringo Smith", role));

        role.clear();

        role.add("Manager");
        peopleList.add(personFactory.createPerson(91125, "Jack Johnson", role));
        peopleList.add(personFactory.createPerson(51622, "Leonardo da Vinci", role));
        peopleList.add(personFactory.createPerson(54125, "Elton John", role));

        role.clear();

        role.add("Student");
        role.add("ContractCleaner");
        peopleList.add(personFactory.createPerson(54126, "Paul Anderson", role));
        peopleList.add(personFactory.createPerson(65478, "Rick Sanchez", role));
        peopleList.add(personFactory.createPerson(21457, "Peter Griffin", role));

        return peopleList; //Returns populated list.
    }

    private List<Campus> createCampusTestData() {

        List<Campus> campusList = new ArrayList<>();

        campusList.add(new Campus("Plymouth"));

        return campusList;
    }

    private List<Building> createBuildingTestData(List<Campus> campus) {

        List<Building> buildingList = new ArrayList<>();

        buildingList.add(new Building("Babbage", campus.get(0)));

        buildingList.add(new Building("Smeaton", campus.get(0)));

        buildingList.add(new Building("Scott", campus.get(0)));

        return buildingList;
    }

    private List<Floor> createFloorTestData(List<Building> building) {
        List<Floor> floorList = new ArrayList<>();

        floorList.add(new Floor(1, building.get(0)));

        floorList.add(new Floor(2, building.get(0)));

        floorList.add(new Floor(3, building.get(0)));

        floorList.add(new Floor(1, building.get(1)));
        floorList.add(new Floor(2, building.get(1)));
        floorList.add(new Floor(3, building.get(1)));
        floorList.add(new Floor(4, building.get(1)));

        floorList.add(new Floor(1, building.get(2)));
        floorList.add(new Floor(2, building.get(2)));
        floorList.add(new Floor(3, building.get(2)));
        floorList.add(new Floor(4, building.get(2)));
        floorList.add(new Floor(5, building.get(2)));

        return floorList;
    }

    private List<Room> createRoomTestData(List<Floor> floor) {
        List<Room> roomList = new ArrayList<>();

        roomList.add(new Room("101", "StudentLab", floor.get(0)));
        roomList.add(new Room("102", "StudentLab", floor.get(0)));
        roomList.add(new Room("103", "StudentLab", floor.get(0)));

        roomList.add(new Room("101", "LectureHall", floor.get(1)));
        roomList.add(new Room("102", "LectureHall", floor.get(1)));
        roomList.add(new Room("103", "StudentLab", floor.get(1)));

        roomList.add(new Room("101", "SecureRoom", floor.get(2)));
        roomList.add(new Room("102", "StudentLab", floor.get(2)));
        roomList.add(new Room("103", "SecureRoom", floor.get(2)));

        return roomList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jLabelUser = new javax.swing.JLabel();
        jLabelViewAndEditUsers = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        panel2 = new java.awt.Panel();
        jLabelCampus = new javax.swing.JLabel();
        jLabelBuilding = new javax.swing.JLabel();
        jLabelFloor = new javax.swing.JLabel();
        jLabelRoom = new javax.swing.JLabel();
        jLabelViewAndEditOperatingMode = new javax.swing.JLabel();
        jComboBoxSetNewModeViewAndEditOperatingMode = new javax.swing.JComboBox<>();
        jLabelSetNewModeViewAndEdit = new javax.swing.JLabel();
        jButtonSaveNewModeViewAndEditOperatingMode = new javax.swing.JButton();
        jComboBoxOperatingMode = new javax.swing.JComboBox<>();
        jComboBoxFloorViewEdit = new javax.swing.JComboBox<>();
        jComboBoxRoomViewEdit = new javax.swing.JComboBox<>();
        jComboBoxBuildingViewEdit = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButtonEditUser = new javax.swing.JButton();
        jLabelCurrentCampusUsers = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListCampusUsers = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        panel3 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaViewCurrentLog = new javax.swing.JTextArea();
        jLabelViewCurrentLog = new javax.swing.JLabel();
        jButtonExit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemSaveLog = new javax.swing.JMenuItem();
        jMenuItemLoadLog = new javax.swing.JMenuItem();
        jMenuItemSaveCampusDataModel = new javax.swing.JMenuItem();
        jMenuItemLoadCampusDataModel = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabelUser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelUser.setText("User");

        jLabelViewAndEditUsers.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelViewAndEditUsers.setText("View & Edit Users");

        jList1.setModel(listModelPeople);
        jScrollPane3.setViewportView(jList1);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabelViewAndEditUsers))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(93, 93, 93)))
                        .addGap(19, 19, 19))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabelUser)
                        .addContainerGap(293, Short.MAX_VALUE))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addComponent(jLabelViewAndEditUsers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        panel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabelCampus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelCampus.setText("Campus");

        jLabelBuilding.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelBuilding.setText("Building");

        jLabelFloor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelFloor.setText("Floor");

        jLabelRoom.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelRoom.setText("Room");

        jLabelViewAndEditOperatingMode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelViewAndEditOperatingMode.setText("View & Edit Operating Mode");

        jComboBoxSetNewModeViewAndEditOperatingMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelSetNewModeViewAndEdit.setText("Set New Mode:");

        jButtonSaveNewModeViewAndEditOperatingMode.setText("Save Mode");

        jComboBoxOperatingMode.setModel(listModelCampus2);
        jComboBoxOperatingMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOperatingModeActionPerformed(evt);
            }
        });

        jComboBoxFloorViewEdit.setModel(listModelFloor);
        jComboBoxFloorViewEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFloorViewEditActionPerformed(evt);
            }
        });

        jComboBoxRoomViewEdit.setModel(listModelRoom);
        jComboBoxRoomViewEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRoomViewEditActionPerformed(evt);
            }
        });

        jComboBoxBuildingViewEdit.setModel(listModelBuilding2);
        jComboBoxBuildingViewEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBuildingViewEditActionPerformed(evt);
            }
        });

        jButton1.setText("Simulate User Entry");

        jButtonEditUser.setText("Edit User");

        jLabelCurrentCampusUsers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCurrentCampusUsers.setText("Current Campus Users");

        jLabel4.setText("Select A User");

        ListCampusUsers.setModel(listModelPeople);
        jScrollPane2.setViewportView(ListCampusUsers);

        jLabel1.setText("Current Building Mode:");

        jLabel5.setText("Current Room Mode:");

        jLabel2.setText("Set New Mode:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Save Mode");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(155, 155, 155))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(jButtonEditUser)
                        .addGap(178, 178, 178))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(142, 142, 142))
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jComboBoxFloorViewEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelRoom)
                            .addComponent(jComboBoxRoomViewEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelCurrentCampusUsers)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel4))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel2Layout.createSequentialGroup()
                                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabelCampus)
                                                .addComponent(jComboBoxOperatingMode, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBoxBuildingViewEdit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addComponent(jLabelBuilding)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel2Layout.createSequentialGroup()
                                        .addComponent(jLabelSetNewModeViewAndEdit)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxSetNewModeViewAndEditOperatingMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonSaveNewModeViewAndEditOperatingMode)))
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabelFloor))))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(jLabelViewAndEditOperatingMode)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(jLabelViewAndEditOperatingMode)
                .addGap(19, 19, 19)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBuilding)
                    .addComponent(jLabelFloor)
                    .addComponent(jLabelRoom)
                    .addComponent(jLabelCampus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxRoomViewEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jComboBoxFloorViewEdit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxOperatingMode)
                    .addComponent(jComboBoxBuildingViewEdit))
                .addGap(57, 57, 57)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSetNewModeViewAndEdit)
                    .addComponent(jComboBoxSetNewModeViewAndEditOperatingMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSaveNewModeViewAndEditOperatingMode)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(jLabelCurrentCampusUsers)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEditUser))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTextAreaViewCurrentLog.setColumns(20);
        jTextAreaViewCurrentLog.setRows(5);
        jScrollPane1.setViewportView(jTextAreaViewCurrentLog);

        jLabelViewCurrentLog.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelViewCurrentLog.setText("View Current Log");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jLabelViewCurrentLog)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addComponent(jLabelViewCurrentLog)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jMenuFile.setText("File");

        jMenuItemSaveLog.setText("Save Log");
        jMenuItemSaveLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveLogActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSaveLog);

        jMenuItemLoadLog.setText("Load Log");
        jMenuFile.add(jMenuItemLoadLog);

        jMenuItemSaveCampusDataModel.setText("Save Campus Data Model");
        jMenuFile.add(jMenuItemSaveCampusDataModel);

        jMenuItemLoadCampusDataModel.setText("Load Campus Data Model");
        jMenuFile.add(jMenuItemLoadCampusDataModel);

        jMenuBar1.add(jMenuFile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 230, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jComboBoxBuildingViewEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBuildingViewEditActionPerformed

        try {
        Integer i = jComboBoxBuildingViewEdit.getSelectedIndex();

        if (i > -1) {
            this.SelectedBuilding = i;
        } else {
            this.SelectedBuilding = 0;
        }

        if (listModelFloor.getSize() > 0) {
            listModelFloor.removeAllElements();
        }

        if (listModelRoom.getSize() > 0) {
            listModelRoom.removeAllElements();
        }

        List<Floor> operatingFloors = this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).getFloorList();

        for (int j = 0; j < operatingFloors.size(); j++) {
            listModelFloor.addElement(operatingFloors.get(j).getFloorNumber());
        }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }//GEN-LAST:event_jComboBoxBuildingViewEditActionPerformed

    private void jComboBoxRoomViewEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRoomViewEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxRoomViewEditActionPerformed

    private void jComboBoxFloorViewEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFloorViewEditActionPerformed
        try {
        Integer i = jComboBoxFloorViewEdit.getSelectedIndex();

        if (i > -1) {
            this.SelectedFloor = i;
        } else {
            this.SelectedFloor = 0;
        }

        if (listModelRoom.getSize() > 0) {
            listModelRoom.removeAllElements();
        }

        List<Room> operatingRoom = this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).getFloorList().get(this.SelectedFloor).getRoomList();

        for (int j = 0; j < operatingRoom.size(); j++) {
            listModelRoom.addElement(operatingRoom.get(j).getRoomName());
        }
        }
        catch (Exception e){
        System.out.println(e);
        }
    }//GEN-LAST:event_jComboBoxFloorViewEditActionPerformed

    private void jComboBoxOperatingModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOperatingModeActionPerformed
        // TODO add your handling code here:
        try {
        Integer i = jComboBoxOperatingMode.getSelectedIndex();

        if (i > -1) {
            this.SelectedCampus = i;
        } else {
            this.SelectedCampus = 0;
        }

        listModelBuilding2.removeAllElements();
        listModelFloor.removeAllElements();
        listModelRoom.removeAllElements();

        Campus operatingCampus = this.campusList.get(i);

        List<Building> buildings = campusList.get(i).getBuildingList();

        for (Integer x = 0; x < buildings.size(); x++) {
            listModelBuilding2.addElement(buildings.get(x).getName());
        }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_jComboBoxOperatingModeActionPerformed

    private void jMenuItemSaveLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveLogActionPerformed
        JFileChooser objFileDialogue = new JFileChooser();
        
        int intDialogueResult = JFileChooser.CANCEL_OPTION;
        
        intDialogueResult = objFileDialogue.showSaveDialog(this);
        
        if(intDialogueResult == JFileChooser.APPROVE_OPTION){
            File objFile = objFileDialogue.getSelectedFile();
            
            
            try (ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(objFile)))){
                
                objOut.writeObject(listModelPeople);
                JOptionPane.showMessageDialog(this,"Current campus users saved", "Save completed", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(IOException e){
                System.out.println(e);
            } 
        }
    }//GEN-LAST:event_jMenuItemSaveLogActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserMainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListCampusUsers;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonEditUser;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonSaveNewModeViewAndEditOperatingMode;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxBuildingViewEdit;
    private javax.swing.JComboBox<String> jComboBoxFloorViewEdit;
    private javax.swing.JComboBox<String> jComboBoxOperatingMode;
    private javax.swing.JComboBox<String> jComboBoxRoomViewEdit;
    private javax.swing.JComboBox<String> jComboBoxSetNewModeViewAndEditOperatingMode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelBuilding;
    private javax.swing.JLabel jLabelCampus;
    private javax.swing.JLabel jLabelCurrentCampusUsers;
    private javax.swing.JLabel jLabelFloor;
    private javax.swing.JLabel jLabelRoom;
    private javax.swing.JLabel jLabelSetNewModeViewAndEdit;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelViewAndEditOperatingMode;
    private javax.swing.JLabel jLabelViewAndEditUsers;
    private javax.swing.JLabel jLabelViewCurrentLog;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemLoadCampusDataModel;
    private javax.swing.JMenuItem jMenuItemLoadLog;
    private javax.swing.JMenuItem jMenuItemSaveCampusDataModel;
    private javax.swing.JMenuItem jMenuItemSaveLog;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaViewCurrentLog;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    // End of variables declaration//GEN-END:variables
}
