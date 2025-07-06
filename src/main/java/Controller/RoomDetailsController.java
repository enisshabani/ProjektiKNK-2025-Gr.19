package Controller;

import Model.Room;
import Model.DTO.RoomDTO;
import Service.RoomService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ResourceBundle;

public class RoomDetailsController {
    @FXML private TableView<Room> roomTable;
    @FXML private TableColumn<Room, Integer> idColumn;
    @FXML private TableColumn<Room, String> firstNameColumn;
    @FXML private TableColumn<Room, String> lastNameColumn;
    @FXML private TableColumn<Room, String> roomColumn;
    @FXML private TableColumn<Room, String> genderColumn;
    @FXML private TableColumn<Room, String> roomstatusColumn;
    @FXML private TableColumn<Room, String> buildingidColumn;
    @FXML private TableColumn<Room, Integer> capacityColumn;
    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private Button languageButton;
    @FXML private Label statusLabel;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;

    private final RoomService roomService;
    private final ResourceBundle bundle;
    private final ObservableList<Room> roomData = FXCollections.observableArrayList();
    private final FilteredList<Room> filteredData = new FilteredList<>(roomData);

    public RoomDetailsController() {
        this.bundle = ResourceBundle.getBundle("language/strings");
        this.roomService = new RoomService(bundle);
    }

    @FXML
    public void initialize() {
        setupColumns();
        loadDataFromDatabase();
        setupSearchFilter();
        setupButtonActions();
        statusLabel.setText("Ready | Admin logged in");
    }

    private void setupColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        roomstatusColumn.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
        buildingidColumn.setCellValueFactory(new PropertyValueFactory<>("building"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
    }

    private void loadDataFromDatabase() {
        roomData.clear();
        roomService.getAllRooms().forEach(dto ->
                roomData.add(new Room(
                        dto.getId(),
                        dto.getFirstName() != null ? dto.getFirstName() : "",
                        dto.getLastName() != null ? dto.getLastName() : "",
                        dto.getRoomNumber(),
                        dto.getGender() != null ? dto.getGender() : "",
                        dto.getRoomStatus(),
                        dto.getBuilding(),
                        dto.getCapacity()
                ))
        );
        roomTable.setItems(new SortedList<>(filteredData));
    }

    private void setupSearchFilter() {
        searchField.textProperty().addListener((obs, oldVal, newVal) ->
                filteredData.setPredicate(room ->
                        newVal == null || newVal.isEmpty() || matchesSearch(room, newVal.toLowerCase())
                )
        );
    }

    private boolean matchesSearch(Room room, String searchTerm) {
        return room.getFirstName().toLowerCase().contains(searchTerm) ||
                room.getLastName().toLowerCase().contains(searchTerm) ||
                room.getRoomNumber().toLowerCase().contains(searchTerm) ||
                room.getGender().toLowerCase().contains(searchTerm) ||
                room.getRoomStatus().toLowerCase().contains(searchTerm) ||
                room.getBuilding().toLowerCase().contains(searchTerm) ||
                String.valueOf(room.getCapacity()).contains(searchTerm);
    }

    private void setupButtonActions() {
        addButton.setOnAction(e -> handleAddRoom());
        editButton.setOnAction(e -> handleEditRoom());
        deleteButton.setOnAction(e -> handleDeleteRoom());
        languageButton.setOnAction(e -> toggleLanguage());
    }

    private void handleAddRoom() {
        Room newRoom = new Room(0, "", "", "", "", "Available", "", 1);
        if (showRoomDialog(newRoom)) {
            String result = roomService.addRoom(convertToDTO(newRoom));
            if ("success".equals(result)) {
                loadDataFromDatabase();
            } else {
                showAlert("Error", result);
            }
        }
    }

    private void handleEditRoom() {
        Room selected = roomTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (showRoomDialog(selected)) {
                String result = roomService.updateRoom(convertToDTO(selected));
                if ("success".equals(result)) {
                    loadDataFromDatabase();
                } else {
                    showAlert("Error", result);
                }
            }
        } else {
            showAlert("No Selection", "Please select a room to edit");
        }
    }

    private void handleDeleteRoom() {
        Room selected = roomTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String result = roomService.deleteRoom(selected.getId());
            if ("success".equals(result)) {
                loadDataFromDatabase();
            } else {
                showAlert("Error", result);
            }
        } else {
            showAlert("No Selection", "Please select a room to delete");
        }
    }

    private boolean showRoomDialog(Room room) {
        // Implement your dialog logic here
        // Return true if user clicked OK, false otherwise
        return true;
    }

    private RoomDTO convertToDTO(Room room) {
        return new RoomDTO(
                room.getId(),
                room.getFirstName(),
                room.getLastName(),
                room.getRoomNumber(),
                room.getGender(),
                room.getRoomStatus(),
                room.getBuilding(),
                room.getCapacity()
        );
    }

    private void showAlert(String title, String message) {
        new Alert(Alert.AlertType.INFORMATION, message).showAndWait();
    }

    private void toggleLanguage() {
        // Implement language toggle logic
    }
}