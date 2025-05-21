package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class StatistikatController {

    @FXML
    private Label totalLabel;
    @FXML
    private Label approvedLabel;
    @FXML
    private Label pendingLabel;
    @FXML
    private Label rejectedLabel;

    private final ObservableList<AplikimetController.Application> applications = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Shembull të dhënash për demonstrim
        applications.addAll(
                new AplikimetController.Application(1, "Shqiperi Mjeku", "2025-05-01", "Pritje"),
                new AplikimetController.Application(2, "Enis Shabani", "2025-05-02", "Refuzuar"),
                new AplikimetController.Application(3, "Rinesa Selmonaj", "2025-05-03", "Aprovuar"),
                new AplikimetController.Application(4, "Diona Krasniqi", "2025-05-04", "Aprovuar"));

        updateStatistics();
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
    }
}