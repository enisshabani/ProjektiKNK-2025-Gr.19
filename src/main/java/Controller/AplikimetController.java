package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class AplikimetController {

    @FXML private Button accountButton;
    @FXML private Button logoutButton;
    @FXML private Label statusLabel;
    @FXML private TextField searchField;
    @FXML private ComboBox<String> filterStatus;
    @FXML private TableView<Application> applicationsTable;
    @FXML private TableColumn<Application, Integer> idColumn;
    @FXML private TableColumn<Application, String> nameColumn;
    @FXML private TableColumn<Application, String> dateColumn;
    @FXML private TableColumn<Application, String> statusColumn;
    @FXML private TableColumn<Application, Void> actionsColumn;

    private ObservableList<Application> applications = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        filterStatus.getItems().addAll("Të gjitha", "Pritje", "Aprovuar", "Refuzuar");
        filterStatus.setValue("Të gjitha");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button approveButton = new Button("Aprovo");
            private final Button rejectButton = new Button("Refuzo");

            {
                approveButton.setOnAction(event -> {
                    Application application = getTableView().getItems().get(getIndex());
                    application.setStatus("Aprovuar");
                    applicationsTable.refresh();
                    statusLabel.setText("Aplikimi u aprovua: " + application.getStudentName());
                });

                rejectButton.setOnAction(event -> {
                    Application application = getTableView().getItems().get(getIndex());
                    application.setStatus("Refuzuar");
                    applicationsTable.refresh();
                    statusLabel.setText("Aplikimi u refuzua: " + application.getStudentName());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(5, approveButton, rejectButton);
                    setGraphic(buttons);
                }
            }
        });
        loadSampleData();
    }

    private void loadSampleData() {
        applications.addAll(
                new Application(1, "Shqiperi Mjeku", "2025-05-01", "Pritje"),
                new Application(2, "Enis Shabani", "2025-05-02", "Pritje"),
                new Application(3, "Rinesa Selmonaj", "2025-05-03", "Aprovuar")
        );
        applicationsTable.setItems(applications);
    }

    @FXML
    private void applyFilters() {
        String searchText = searchField.getText().toLowerCase();
        String statusFilter = filterStatus.getValue();

        ObservableList<Application> filteredList = FXCollections.observableArrayList();

        for (Application app : applications) {
            boolean matchesSearch = app.getStudentName().toLowerCase().contains(searchText);
            boolean matchesStatus = statusFilter.equals("Të gjitha") || app.getStatus().equals(statusFilter);

            if (matchesSearch && matchesStatus) {
                filteredList.add(app);
            }
        }

        applicationsTable.setItems(filteredList);
        statusLabel.setText("Filtrimi u aplikua. Gjetur: " + filteredList.size() + " aplikime");
    }

    @FXML
    private void handleAccountButton() {
        statusLabel.setText("Hapja e faqes së llogarisë...");
    }

    @FXML
    private void handleLogoutButton() {
        statusLabel.setText("Po dilni nga sistemi...");
    }

    public static class Application {
        private final int id;
        private final String studentName;
        private final String applicationDate;
        private String status;

        public Application(int id, String studentName, String applicationDate, String status) {
            this.id = id;
            this.studentName = studentName;
            this.applicationDate = applicationDate;
            this.status = status;
        }

        public int getId() { return id; }
        public String getStudentName() { return studentName; }
        public String getApplicationDate() { return applicationDate; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}