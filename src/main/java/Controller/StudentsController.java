package Controller;

import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

public class StudentsController {

    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableColumn<Student, Integer> idColumn;

    @FXML
    private TableColumn<Student, String> firstNameColumn;

    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private TableColumn<Student, String> emailColumn;

    @FXML
    private TableColumn<Student, String> genderColumn;

    @FXML
    private TableColumn<Student, String> schoolColumn;

    @FXML
    private TableColumn<Student, Integer> yearOfStudyColumn;

    @FXML
    private TableColumn<Student, String> cityColumn;

    @FXML
    private TableColumn<Student, String> statusColumn;

    @FXML
    private ComboBox<String> filterStatus;

    @FXML
    private TextField searchField;

    private final ObservableList<Student> masterData = FXCollections.observableArrayList();
    private FilteredList<Student> filteredData;

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        schoolColumn.setCellValueFactory(new PropertyValueFactory<>("school"));
        yearOfStudyColumn.setCellValueFactory(new PropertyValueFactory<>("yearOfStudy"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("cityOfOrigin"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        filteredData = new FilteredList<>(masterData, p -> true);
        studentsTable.setItems(filteredData);

        filterStatus.getItems().addAll("Të gjithë", "Aktiv", "Jo aktiv");
        filterStatus.setValue("Të gjithë");

        filterStatus.setOnAction(e -> applyFilters());

        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldVal, newVal) -> applyFilters());
        }
    }

    @FXML
    public void applyFilters() {
        String selectedStatus = filterStatus.getValue();
        String search = searchField != null ? searchField.getText().toLowerCase() : "";

        filteredData.setPredicate(student -> {
            boolean matchesStatus = selectedStatus.equals("Të gjithë")
                    || student.getStatus().equalsIgnoreCase(selectedStatus);
            boolean matchesSearch = search.isEmpty()
                    || student.getFirstName().toLowerCase().contains(search)
                    || student.getLastName().toLowerCase().contains(search)
                    || student.getEmail().toLowerCase().contains(search);

            return matchesStatus && matchesSearch;
        });
    }
}


