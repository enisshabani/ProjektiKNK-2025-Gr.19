package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Locale;
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

    @FXML private Button languageButton;
    @FXML private Button searchButton;

    private ResourceBundle bundle;
    private boolean isAlbanian = true;

    private ObservableList<Model.Room> roomData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        roomColumn.setCellValueFactory(cellData -> cellData.getValue().roomNumberProperty());
        genderColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        roomstatusColumn.setCellValueFactory(cellData -> cellData.getValue().roomStatusProperty());
        buildingidColumn.setCellValueFactory(cellData -> cellData.getValue().buildingIdProperty().asObject());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty().asObject());


        roomData.addAll(
                new Model.Room(1, "Ermal", "Hoxha", "101", "M", "X", 1, 2),
                new Model.Room(2, "Anisa", "Bardhi", "102", "F", "✓", 1, 2),
                new Model.Room(3, "Besnik", "Dervishi", "103", "M", "X", 2, 3),
                new Model.Room(4, "Drita", "Kastrati", "104", "F", "✓", 1, 1),
                new Model.Room(5, "Fisnik", "Berisha", "105", "M", "X", 3, 4),
                new Model.Room(6, "Gentiana", "Gjoka", "106", "F", "✓", 2, 2),
                new Model.Room(7, "Hektor", "Kulla", "107", "M", "X", 1, 3),
                new Model.Room(8, "Ilirjana", "Mema", "108", "F", "✓", 2, 1),
                new Model.Room(9, "Jeton", "Prifti", "109", "M", "X", 4, 4),
                new Model.Room(10, "Klea", "Shkreli", "110", "F", "✓", 1, 2),
                new Model.Room(11, "Liridon", "Toska", "111", "M", "X", 2, 3),
                new Model.Room(12, "Majlinda", "Vogli", "112", "F", "✓", 3, 2),
                new Model.Room(13, "Nderim", "Zeka", "113", "M", "X", 1, 1),
                new Model.Room(14, "Ornela", "Xhafa", "114", "F", "✓", 2, 4),
                new Model.Room(15, "Pellumb", "Basha", "115", "M", "X", 3, 3),
                new Model.Room(16, "Rozafa", "Cani", "116", "F", "✓", 1, 2),
                new Model.Room(17, "Shpend", "Deda", "117", "M", "X", 4, 1),
                new Model.Room(18, "Teuta", "Elbasani", "118", "F", "✓", 2, 2),
                new Model.Room(19, "Valon", "Fazliu", "119", "M", "X", 3, 4),
                new Model.Room(20, "Zana", "Gjinali", "120", "F", "✓", 1, 3)
        );

        roomTable.setItems(roomData);
        statusLabel.setText("Loaded " + roomData.size() + " rooms.");

        bundle = ResourceBundle.getBundle("language/strings", new Locale("al"));
        languageButton.setOnAction(e -> toggleLanguage());
        updateUI();
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

    private void toggleLanguage() {
        isAlbanian = !isAlbanian;
        if (isAlbanian) {
            bundle = ResourceBundle.getBundle("language/strings", new Locale("al"));
            languageButton.setText("AL | EN");
        } else {
            bundle = ResourceBundle.getBundle("language/strings", new Locale("en"));
            languageButton.setText("EN | SQ");
        }
        updateUI();
    }

    private void updateUI() {
        accountButton.setText(bundle.getString("accountButton"));
        logoutButton.setText(bundle.getString("loginButton"));
        searchButton.setText(bundle.getString("continueButton"));
        idColumn.setText(bundle.getString("idColumn"));
        firstNameColumn.setText(bundle.getString("nameLabel"));
        lastNameColumn.setText(bundle.getString("lastnameLabel"));
        roomColumn.setText(bundle.getString("roomColumn"));
        genderColumn.setText(bundle.getString("genderLabel"));
        roomstatusColumn.setText(bundle.getString("roomStatusColumn"));
        buildingidColumn.setText(bundle.getString("buildingColumn"));
        capacityColumn.setText(bundle.getString("capacityColumn"));
        searchField.setPromptText(bundle.getString("searchPrompt"));
    }
}
