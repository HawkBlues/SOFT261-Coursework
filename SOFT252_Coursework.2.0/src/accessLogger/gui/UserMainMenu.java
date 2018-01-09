/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessLogger.gui;

import Buidlings.Building;
import Buidlings.Campus;
import Buidlings.Floor;
import Buidlings.Room;
import accessPeople.PeopleFactory;
import accessPeople.Person;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import soft252_coursework.pkg2.pkg0.SOFT252_Coursework20;

/**
 *
 *
 */
public class UserMainMenu extends javax.swing.JFrame {

    private DefaultListModel listModelPeople;
    private DefaultListModel listModelCampus;
    private DefaultListModel listModelBuilding;
    private DefaultListModel listModelFloor;
    private DefaultListModel listModelRoom;
    private List<Person> personList;
    private PeopleFactory personFactory;
    private List<Campus> campusList;
    private String buildingName = "Babbage";
    private Integer floorNumber = 0;

    /**
     * Creates new form UserMainMenu
     */
    public UserMainMenu() {

        listModelFloor = new DefaultListModel();
        listModelRoom = new DefaultListModel();

        List<Person> peopleList = new ArrayList<>();

        peopleList = createPersonTestData();

        listModelPeople = new DefaultListModel();

        for (Integer i = 0; i < peopleList.size(); i++) {
            listModelPeople.addElement(peopleList.get(i).getMySwipeCard().getName());//Current Campus user list on GUI is populated from listModelPeople.
        }

        populateSites();

        // populateFloors(buildingList);
        initComponents();
    }

    private void populateSites() {
        List<Campus> campusList = new ArrayList<>();
        campusList = populateCampus();

        List<Building> buildingList = new ArrayList<>();
        buildingList = populateBuilding(campusList);

        List<Floor> floorList = new ArrayList<>();
        floorList = populateFloors(buildingList);

        populateRooms(floorList);

    }

    private List<Campus> populateCampus() {
        listModelCampus = new DefaultListModel();
        List<Campus> campusList = new ArrayList<>();
        campusList = createCampusTestData();

        for (Integer i = 0; i < campusList.size(); i++) {
            listModelCampus.addElement(campusList.get(i).getName());
        }
        return campusList;
    }

    private List<Building> populateBuilding(List<Campus> campusList) {
        listModelBuilding = new DefaultListModel();
        List<Building> buildingList = new ArrayList<>();
        buildingList = createBuildingTestData(campusList);

        for (Integer i = 0; i < buildingList.size(); i++) {
            listModelBuilding.addElement(buildingList.get(i).getName());
        }
        return buildingList;
    }

    private List<Floor> populateFloors(List<Building> buildingList) {

        List<Floor> floorList = new ArrayList<>();

        floorList = createFloorTestData(buildingList);

        for (Integer i = 0; i < floorList.size(); i++) {
            if (floorList.get(i).getBuilding().getName() == buildingName) {
                listModelFloor.addElement(floorList.get(i).getFloorNumber());//Have default value of 0 (getting first building name), when user clicks another, get that index and update it.
            }

        }
        return floorList;
    }

    private void populateRooms(List<Floor> floorList) {

        List<Room> roomList = new ArrayList<>();

        roomList = createRoomTestData(floorList);

        for (Integer i = 0; i < roomList.size(); i++) {

            if (roomList.get(i).getFloor().getFloorNumber() == floorNumber) {

                listModelRoom.addElement(roomList.get(i).getRoomName());
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabelCurrentCampusUsers = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListCampusUsers = new javax.swing.JList<>();
        panel1 = new java.awt.Panel();
        jLabelUser = new javax.swing.JLabel();
        jButtonEditUser = new javax.swing.JButton();
        jLabelViewAndEditUsers = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        panel2 = new java.awt.Panel();
        jLabelCampus = new javax.swing.JLabel();
        jLabelBuilding = new javax.swing.JLabel();
        jLabelFloor = new javax.swing.JLabel();
        jLabelRoom = new javax.swing.JLabel();
        jLabelViewAndEditOperatingMode = new javax.swing.JLabel();
        jLabelCurrentModeViewAndEditOperatingMode = new javax.swing.JLabel();
        jComboBoxSetNewModeViewAndEditOperatingMode = new javax.swing.JComboBox<>();
        jLabelSetNewModeViewAndEdit = new javax.swing.JLabel();
        jButtonSaveNewModeViewAndEditOperatingMode = new javax.swing.JButton();
        jLabelShowCurrentModeViewAndEditOperatingMode = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListBuildingList = new javax.swing.JList<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        jListFloorList = new javax.swing.JList<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        jListRoomList = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabelSimulateUserEntryAttempt = new javax.swing.JLabel();
        jComboBoxUserSimulateUserEntryAttempt = new javax.swing.JComboBox<>();
        jLabelUserSimulateUserEntryAttempt = new javax.swing.JLabel();
        jComboBoxBuildingSimulateUserEntryAttempt = new javax.swing.JComboBox<>();
        jComboBoxCampusSimulateUserEntryAttempt = new javax.swing.JComboBox<>();
        jComboBoxFloorSimulateUserEntryAttempt = new javax.swing.JComboBox<>();
        jComboBoxRoomSimulateUserEntryAttempt = new javax.swing.JComboBox<>();
        jLabelRoomSimulateUserEntryAttempt = new javax.swing.JLabel();
        jLabelFloorSimulateUserEntryAttempt = new javax.swing.JLabel();
        jLabelBuildingSimulateUserEntryAttempt = new javax.swing.JLabel();
        jLabelCampusSimulateUserEntryAttempt = new javax.swing.JLabel();
        jButtonGoSimulateUserEntryAttempt = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
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

        jLabelCurrentCampusUsers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCurrentCampusUsers.setText("Current Campus Users");

        ListCampusUsers.setModel(listModelPeople);
        jScrollPane2.setViewportView(ListCampusUsers);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCurrentCampusUsers)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jLabelCurrentCampusUsers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        panel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabelUser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelUser.setText("User");

        jButtonEditUser.setText("Edit User");
        jButtonEditUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonEditUserMouseClicked(evt);
            }
        });

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
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelViewAndEditUsers, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonEditUser, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(19, 19, 19))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabelUser)
                        .addContainerGap(291, Short.MAX_VALUE))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addComponent(jLabelViewAndEditUsers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEditUser)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

        jLabelCurrentModeViewAndEditOperatingMode.setText("Current Mode:");

        jComboBoxSetNewModeViewAndEditOperatingMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelSetNewModeViewAndEdit.setText("Set New Mode:");

        jButtonSaveNewModeViewAndEditOperatingMode.setText("Save Mode");

        jLabelShowCurrentModeViewAndEditOperatingMode.setText("jLabel1");

        jList2.setModel(listModelCampus);
        jScrollPane4.setViewportView(jList2);

        jListBuildingList.setModel(listModelBuilding);
        jListBuildingList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListBuildingListMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jListBuildingList);

        jListFloorList.setModel(listModelFloor);
        jListFloorList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListFloorListMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jListFloorList);

        jListRoomList.setModel(listModelRoom);
        jListRoomList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListRoomListMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jListRoomList);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSetNewModeViewAndEdit)
                            .addComponent(jLabelCurrentModeViewAndEditOperatingMode))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addComponent(jComboBoxSetNewModeViewAndEditOperatingMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSaveNewModeViewAndEditOperatingMode))
                            .addComponent(jLabelShowCurrentModeViewAndEditOperatingMode, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(142, 142, 142))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelViewAndEditOperatingMode)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(jLabelCampus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelBuilding)
                                        .addGap(14, 14, 14)))
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabelFloor)
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabelRoom))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(117, 117, 117))))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jLabelViewAndEditOperatingMode)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelCampus)
                                        .addComponent(jLabelFloor)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabelRoom)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelBuilding)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCurrentModeViewAndEditOperatingMode)
                    .addComponent(jLabelShowCurrentModeViewAndEditOperatingMode))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSetNewModeViewAndEditOperatingMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSaveNewModeViewAndEditOperatingMode)
                    .addComponent(jLabelSetNewModeViewAndEdit))
                .addGap(136, 136, 136))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabelSimulateUserEntryAttempt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelSimulateUserEntryAttempt.setText("Simulate User Entry Attempt");

        jComboBoxUserSimulateUserEntryAttempt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelUserSimulateUserEntryAttempt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelUserSimulateUserEntryAttempt.setText("User");

        jComboBoxBuildingSimulateUserEntryAttempt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxCampusSimulateUserEntryAttempt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxFloorSimulateUserEntryAttempt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxRoomSimulateUserEntryAttempt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelRoomSimulateUserEntryAttempt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelRoomSimulateUserEntryAttempt.setText("Room");

        jLabelFloorSimulateUserEntryAttempt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelFloorSimulateUserEntryAttempt.setText("Floor");

        jLabelBuildingSimulateUserEntryAttempt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelBuildingSimulateUserEntryAttempt.setText("Building");

        jLabelCampusSimulateUserEntryAttempt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelCampusSimulateUserEntryAttempt.setText("Campus");

        jButtonGoSimulateUserEntryAttempt.setText("Go");

        jLabel17.setText("Simulate user entrance attempt");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxUserSimulateUserEntryAttempt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelUserSimulateUserEntryAttempt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelCampusSimulateUserEntryAttempt)
                                .addGap(39, 39, 39)
                                .addComponent(jLabelBuildingSimulateUserEntryAttempt)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelFloorSimulateUserEntryAttempt)
                                .addGap(37, 37, 37)
                                .addComponent(jLabelRoomSimulateUserEntryAttempt))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBoxCampusSimulateUserEntryAttempt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxBuildingSimulateUserEntryAttempt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxFloorSimulateUserEntryAttempt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxRoomSimulateUserEntryAttempt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jButtonGoSimulateUserEntryAttempt)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(67, Short.MAX_VALUE)
                        .addComponent(jLabelSimulateUserEntryAttempt)
                        .addGap(73, 73, 73)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSimulateUserEntryAttempt)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelUserSimulateUserEntryAttempt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxUserSimulateUserEntryAttempt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCampusSimulateUserEntryAttempt)
                            .addComponent(jLabelBuildingSimulateUserEntryAttempt)
                            .addComponent(jLabelFloorSimulateUserEntryAttempt)
                            .addComponent(jLabelRoomSimulateUserEntryAttempt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxCampusSimulateUserEntryAttempt)
                            .addComponent(jComboBoxBuildingSimulateUserEntryAttempt)
                            .addComponent(jComboBoxFloorSimulateUserEntryAttempt)
                            .addComponent(jComboBoxRoomSimulateUserEntryAttempt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(47, 47, 47)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGoSimulateUserEntryAttempt)
                .addContainerGap(19, Short.MAX_VALUE))
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonExit)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExit))
                    .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonEditUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEditUserMouseClicked
        ViewAndEditUser objWindow = new ViewAndEditUser();
        objWindow.setVisible(true);
    }//GEN-LAST:event_jButtonEditUserMouseClicked

    private void jListRoomListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListRoomListMouseClicked
        listModelRoom.clear();
  //      floorNumber = jListRoomList.getSelectedIndex();
       populateSites();
    }//GEN-LAST:event_jListRoomListMouseClicked

    private void jListFloorListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListFloorListMouseClicked
       
       
        floorNumber = jListFloorList.getSelectedIndex();
        listModelRoom.clear();
        listModelFloor.clear();
        populateSites();
    }//GEN-LAST:event_jListFloorListMouseClicked

    private void jListBuildingListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListBuildingListMouseClicked
      //  listModelBuilding.clear();
      listModelRoom.clear();
      listModelFloor.clear();
        buildingName = jListBuildingList.getSelectedValue();
        populateSites();
    }//GEN-LAST:event_jListBuildingListMouseClicked

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
    private javax.swing.JButton jButtonEditUser;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonGoSimulateUserEntryAttempt;
    private javax.swing.JButton jButtonSaveNewModeViewAndEditOperatingMode;
    private javax.swing.JComboBox<String> jComboBoxBuildingSimulateUserEntryAttempt;
    private javax.swing.JComboBox<String> jComboBoxCampusSimulateUserEntryAttempt;
    private javax.swing.JComboBox<String> jComboBoxFloorSimulateUserEntryAttempt;
    private javax.swing.JComboBox<String> jComboBoxRoomSimulateUserEntryAttempt;
    private javax.swing.JComboBox<String> jComboBoxSetNewModeViewAndEditOperatingMode;
    private javax.swing.JComboBox<String> jComboBoxUserSimulateUserEntryAttempt;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabelBuilding;
    private javax.swing.JLabel jLabelBuildingSimulateUserEntryAttempt;
    private javax.swing.JLabel jLabelCampus;
    private javax.swing.JLabel jLabelCampusSimulateUserEntryAttempt;
    private javax.swing.JLabel jLabelCurrentCampusUsers;
    private javax.swing.JLabel jLabelCurrentModeViewAndEditOperatingMode;
    private javax.swing.JLabel jLabelFloor;
    private javax.swing.JLabel jLabelFloorSimulateUserEntryAttempt;
    private javax.swing.JLabel jLabelRoom;
    private javax.swing.JLabel jLabelRoomSimulateUserEntryAttempt;
    private javax.swing.JLabel jLabelSetNewModeViewAndEdit;
    private javax.swing.JLabel jLabelShowCurrentModeViewAndEditOperatingMode;
    private javax.swing.JLabel jLabelSimulateUserEntryAttempt;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelUserSimulateUserEntryAttempt;
    private javax.swing.JLabel jLabelViewAndEditOperatingMode;
    private javax.swing.JLabel jLabelViewAndEditUsers;
    private javax.swing.JLabel jLabelViewCurrentLog;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jListBuildingList;
    private javax.swing.JList<String> jListFloorList;
    private javax.swing.JList<String> jListRoomList;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemLoadCampusDataModel;
    private javax.swing.JMenuItem jMenuItemLoadLog;
    private javax.swing.JMenuItem jMenuItemSaveCampusDataModel;
    private javax.swing.JMenuItem jMenuItemSaveLog;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextAreaViewCurrentLog;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    // End of variables declaration//GEN-END:variables
}
