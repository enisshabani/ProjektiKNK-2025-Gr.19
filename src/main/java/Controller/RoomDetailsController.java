package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomDetailsController implements Initializable {

    @FXML private TableView<Model.Room> roomTable;
    @FXML private TableColumn<Model.Room, Integer> idColumn;
    @FXML private TableColumn<Model.Room, String> firstNameColumn;
    @FXML private TableColumn<Model.Room, String> lastNameColumn;
    @FXML private TableColumn<Model.Room, String> roomColumn;
    @FXML private TableColumn<Model.Room, String> genderColumn;
    @FXML private TableColumn<Model.Room, String> roomstatusColumn;
    @FXML private TableColumn<Model.Room, Integer> buildingidColumn;
    @FXML private TableColumn<Model.Room, Integer> capacityColumn;

    @FXML private TextField searchField;
    @FXML private Label statusLabel;
    @FXML private Button accountButton;
    @FXML private Button logoutButton;

    private ObservableList<Model.Room> roomData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set up columns
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        roomColumn.setCellValueFactory(cellData -> cellData.getValue().roomNumberProperty());
        genderColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        roomstatusColumn.setCellValueFactory(cellData -> cellData.getValue().roomStatusProperty());
        buildingidColumn.setCellValueFactory(cellData -> cellData.getValue().buildingIdProperty().asObject());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty().asObject());

        // Dummy data
        roomData.addAll(
                new Model.Room(1, "Alice", "Smith", "101", "Female", "Occupied", 1, 2),
                new Model.Room(2, "Bob", "Johnson", "102", "Male", "Available", 1, 2)
        );

        roomTable.setItems(roomData);
        statusLabel.setText("Loaded " + roomData.size() + " rooms.");
    }

    @FXML
    private void applyFilters() {
        String keyword = searchField.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            roomTable.setItems(roomData);
        } else {
            ObservableList<Model.Room> filtered = FXCollections.observableArrayList();
            for (Model.Room room : roomData) {
                if (room.getRoomNumber().toLowerCase().contains(keyword)) {
                    filtered.add(room);
                }
            }
            roomTable.setItems(filtered);
        }
    }
}
