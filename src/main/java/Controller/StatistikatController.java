package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatistikatController {

    @FXML
    private Button accountButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private Label approvedLabel;
    @FXML
    private Label pendingLabel;
    @FXML
    private Label rejectedLabel;
    @FXML
    private TableView<StatEntry> statisticsTable;
    @FXML
    private TableColumn<StatEntry, String> statusTypeColumn;
    @FXML
    private TableColumn<StatEntry, Integer> countColumn;

    private final ObservableList<AplikimetController.Application> applications = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        statusTypeColumn.setCellValueFactory(new PropertyValueFactory<>("statusType"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        loadSampleData();
        updateStatistics();
    }

    private void loadSampleData() {
        applications.addAll(
                new AplikimetController.Application(1, "Shqiperi Mjeku", "2025-05-01", "Pritje"),
                new AplikimetController.Application(2, "Enis Shabani", "2025-05-02", "Refuzuar"),
                new AplikimetController.Application(3, "Rinesa Selmonaj", "2025-05-03", "Aprovuar"),
                new AplikimetController.Application(4, "Diona Krasniqi", "2025-05-04", "Aprovuar"));
    }

    private void updateStatistics() {
        int total = applications.size();
        long approved = applications.stream().filter(a -> a.getStatus().equals("Aprovuar")).count();
        long pending = applications.stream().filter(a -> a.getStatus().equals("Pritje")).count();
        long rejected = applications.stream().filter(a -> a.getStatus().equals("Refuzuar")).count();

        totalLabel.setText("Gjithsej: " + total);
        approvedLabel.setText("Të Aprovuar: " + approved);
        pendingLabel.setText("Në Pritje: " + pending);
        rejectedLabel.setText("Të Refuzuar: " + rejected);

        ObservableList<StatEntry> stats = FXCollections.observableArrayList(
                new StatEntry("Aprovuar", (int) approved),
                new StatEntry("Pritje", (int) pending),
                new StatEntry("Refuzuar", (int) rejected));

        statisticsTable.setItems(stats);
    }

    @FXML
    private void handleLogoutButton() {
        statusLabel.setText("Po dilni nga sistemi...");
    }

    @FXML
    private void handleAccountButton() {
        statusLabel.setText("Hapja e faqes së llogarisë...");
    }

    public static class StatEntry {
        private final String statusType;
        private final int count;

        public StatEntry(String statusType, int count) {
            this.statusType = statusType;
            this.count = count;
        }

        public String getStatusType() {
            return statusType;
        }

        public int getCount() {
            return count;
        }
    }
}