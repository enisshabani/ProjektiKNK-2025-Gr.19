package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.Room;

public class RoomDetailsController {

    @FXML
    private TableView<Room> roomTable;

    @FXML
    private TableColumn<Room, Integer> idColumn;

    @FXML
    private TableColumn<Room, String> firstNameColumn;

    @FXML
    private TableColumn<Room, String> lastNameColumn;

    @FXML
    private TableColumn<Room, String> roomColumn;

    @FXML
    private TableColumn<Room, String> genderColumn;

    @FXML
    private TableColumn<Room, String> roomstatusColumn;

    @FXML
    private TableColumn<Room, String> buildingidColumn;

    @FXML
    private TableColumn<Room, Integer> capacityColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Button languageButton;

    @FXML
    private Label statusLabel;

    private ObservableList<Room> roomData = FXCollections.observableArrayList();
    private FilteredList<Room> filteredData = new FilteredList<>(roomData, p -> true);

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        roomstatusColumn.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
        buildingidColumn.setCellValueFactory(new PropertyValueFactory<>("building"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        loadSampleData();

        setupSearchFilter();

        statusLabel.setText("Ready | Admin logged in");

        languageButton.setOnAction(event -> toggleLanguage());
    }

    private void loadSampleData() {
        // This is sample data - replace with your actual data loading logic
        roomData.add(new Room(1, "John", "Doe", "101", "Male", "Occupied", "Building A", 2));
        roomData.add(new Room(2, "Jane", "Smith", "102", "Female", "Occupied", "Building A", 2));
        roomData.add(new Room(3, "Alice", "Johnson", "201", "Female", "Available", "Building B", 3));
        roomData.add(new Room(4, "Bob", "Williams", "202", "Male", "Maintenance", "Building B", 3));

        SortedList<Room> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(roomTable.comparatorProperty());

        roomTable.setItems(sortedData);
    }

    private void setupSearchFilter() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(room -> {
                // If filter text is empty, display all rooms
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (room.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (room.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (room.getRoomNumber().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (room.getGender().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (room.getRoomStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (room.getBuilding().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(room.getCapacity()).contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }

    @FXML
    private void applyFilters() {
    }

    private void toggleLanguage() {

        if (languageButton.getText().equals("AL | EN")) {
            // Switch to English
            languageButton.setText("EN | AL");
            statusLabel.setText("Ready | Admin logged in");
            idColumn.setText("ID");
            firstNameColumn.setText("First Name");
            lastNameColumn.setText("Last Name");
            roomColumn.setText("Room Number");
            genderColumn.setText("Gender");
            roomstatusColumn.setText("Room Status");
            buildingidColumn.setText("Building");
            capacityColumn.setText("Capacity");
        } else {
            // Switch to Albanian
            languageButton.setText("AL | EN");
            statusLabel.setText("Gati | Admin i kyqur");
            idColumn.setText("ID");
            firstNameColumn.setText("Emri");
            lastNameColumn.setText("Mbiemri");
            roomColumn.setText("Numri i Dhomes");
            genderColumn.setText("Gjinia");
            roomstatusColumn.setText("Statusi i Dhomes");
            buildingidColumn.setText("Ndertesa");
            capacityColumn.setText("Kapaciteti");
        }
    }

    public void setRoomData(ObservableList<Room> data) {
        this.roomData = data;
        this.filteredData = new FilteredList<>(roomData, p -> true);
        setupSearchFilter();
    }
}