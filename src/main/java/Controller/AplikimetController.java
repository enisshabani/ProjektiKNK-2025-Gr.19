package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

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
    @FXML private Button languageButton; 

    private ObservableList<Application> applications = FXCollections.observableArrayList();
    private ResourceBundle bundle;
    private boolean isAlbanian = true;

    @FXML
    public void initialize() {
        loadLanguage(isAlbanian);
        setupTableColumns();
        loadSampleData();

        filterStatus.setOnAction(e -> applyFilters());
        searchField.textProperty().addListener((obs, oldVal, newVal) -> applyFilters());
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button approveButton = new Button();
            private final Button rejectButton = new Button();

            {
                approveButton.setOnAction(event -> {
                    Application application = getTableView().getItems().get(getIndex());
                    application.setStatus(bundle.getString("filterStatusAprovuar"));
                    applicationsTable.refresh();
                    statusLabel.setText(bundle.getString("applicationApprovedStatus") + ": " + application.getStudentName());
                });

                rejectButton.setOnAction(event -> {
                    Application application = getTableView().getItems().get(getIndex());
                    application.setStatus(bundle.getString("filterStatusRefuzuar"));
                    applicationsTable.refresh();
                    statusLabel.setText(bundle.getString("applicationRejectedStatus") + ": " + application.getStudentName());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                   
                    approveButton.setText(bundle.getString("approveButton"));
                    rejectButton.setText(bundle.getString("rejectButton"));
                    HBox buttons = new HBox(5, approveButton, rejectButton);
                    setGraphic(buttons);
                }
            }
        });
    }

    private void loadSampleData() {
        applications.addAll(
                new Application(1, "Shqiperi Mjeku", "2025-05-01", bundle.getString("filterStatusPritje")),
                new Application(2, "Enis Shabani", "2025-05-02", bundle.getString("filterStatusPritje")),
                new Application(3, "Rinesa Selmonaj", "2025-05-03", bundle.getString("filterStatusAprovuar"))
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
            boolean matchesStatus = statusFilter.equals(bundle.getString("filterAll")) || app.getStatus().equals(statusFilter);

            if (matchesSearch && matchesStatus) {
                filteredList.add(app);
            }
        }

        applicationsTable.setItems(filteredList);
        statusLabel.setText(bundle.getString("filtersAppliedStatus") + ": " + filteredList.size());
    }

    @FXML
    private void handleAccountButton() {
        statusLabel.setText(bundle.getString("accountPageStatus"));
    }

    @FXML
    private void handleLogoutButton() {
        statusLabel.setText(bundle.getString("loggingOutStatus"));
       
    }

    @FXML
    private void handleLanguageSwitch(ActionEvent event) {
        isAlbanian = !isAlbanian;
        loadLanguage(isAlbanian);
    }

    private void loadLanguage(boolean isAlbanian) {
        Locale locale = isAlbanian ? new Locale("al") : Locale.ENGLISH;
        bundle = ResourceBundle.getBundle("language.strings", locale);

        accountButton.setText(bundle.getString("accountButton"));
        logoutButton.setText(bundle.getString("logoutButton"));
        searchField.setPromptText(bundle.getString("searchPrompt"));

       
        String selectedStatus = filterStatus.getValue();

        filterStatus.getItems().clear();
        filterStatus.getItems().addAll(
                bundle.getString("filterAll"),
                bundle.getString("filterStatusPritje"),
                bundle.getString("filterStatusAprovuar"),
                bundle.getString("filterStatusRefuzuar")
        );

        
        if (filterStatus.getItems().contains(selectedStatus)) {
            filterStatus.setValue(selectedStatus);
        } else {
            filterStatus.setValue(bundle.getString("filterAll"));
        }


        idColumn.setText(bundle.getString("idColumn"));
        nameColumn.setText(bundle.getString("nameColumn"));
        dateColumn.setText(bundle.getString("dateColumn"));
        statusColumn.setText(bundle.getString("statusColumn"));
        actionsColumn.setText(bundle.getString("actionsColumn"));
        languageButton.setText(isAlbanian ? "EN" : "SQ");
        statusLabel.setText(""); 

       
        applicationsTable.refresh();
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
