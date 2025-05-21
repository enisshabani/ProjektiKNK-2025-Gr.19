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
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class StudentsController {

    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableColumn<Student, Integer> idcolumn;

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

    @FXML
    private Button searchButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Button languageButton;

    private final ObservableList<Student> masterData = FXCollections.observableArrayList();
    private FilteredList<Student> filteredData;

    private ResourceBundle bundle;
    private boolean isAlbanian = true;

    public void initialize() {
        setupTableColumns();
        filteredData = new FilteredList<>(masterData, p -> true);
        studentsTable.setItems(filteredData);

        loadLanguage(isAlbanian);

        filterStatus.setOnAction(e -> applyFilters());

        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldVal, newVal) -> applyFilters());
        }

        if (languageButton != null) {
            languageButton.setOnAction(e -> handleLanguageSwitch());
        }
    }

    private void setupTableColumns() {
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        schoolColumn.setCellValueFactory(new PropertyValueFactory<>("school"));
        yearOfStudyColumn.setCellValueFactory(new PropertyValueFactory<>("yearOfStudy"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("cityOfOrigin"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void handleLanguageSwitch() {
        isAlbanian = !isAlbanian;
        loadLanguage(isAlbanian);
    }

    private void loadLanguage(boolean isAlbanian) {
        try {
            Locale locale = isAlbanian ? new Locale("al") : Locale.ENGLISH;
            bundle = ResourceBundle.getBundle("language.strings", locale);

            if (titleLabel != null) {
                titleLabel.setText(bundle.getString("titleLabel"));
            }

            if (searchField != null) {
                searchField.setPromptText(bundle.getString("searchPlaceholder"));
            }

            if (filterStatus != null) {
                filterStatus.getItems().clear();
                filterStatus.getItems().addAll(
                        bundle.getString("filterAll"),
                        bundle.getString("filterActive"),
                        bundle.getString("filterInactive")
                );
                filterStatus.setValue(bundle.getString("filterAll"));
            }

            if (searchButton != null) {
                searchButton.setText(bundle.getString("searchButton"));
            }

            if (languageButton != null) {
                languageButton.setText(bundle.getString("buttonLanguage"));
            }

            idcolumn.setText(bundle.getString("idcolumn"));
            firstNameColumn.setText(bundle.getString("firstNameColumn"));
            lastNameColumn.setText(bundle.getString("lastNameColumn"));
            emailColumn.setText(bundle.getString("emailColumn"));
            genderColumn.setText(bundle.getString("genderColumn"));
            schoolColumn.setText(bundle.getString("schoolColumn"));
            yearOfStudyColumn.setText(bundle.getString("yearColumn"));
            cityColumn.setText(bundle.getString("cityColumn"));
            statusColumn.setText(bundle.getString("statusColumn"));

        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void applyFilters() {
        String selectedStatus = filterStatus.getValue();
        String search = searchField != null ? searchField.getText().toLowerCase() : "";

        filteredData.setPredicate(student -> {
            boolean matchesStatus = selectedStatus.equals(bundle.getString("filterAll")) ||
                    student.getStatus().equalsIgnoreCase(selectedStatus);
            boolean matchesSearch = search.isEmpty()
                    || student.getFirstName().toLowerCase().contains(search)
                    || student.getLastName().toLowerCase().contains(search)
                    || student.getEmail().toLowerCase().contains(search);

            return matchesStatus && matchesSearch;
        });
    }
}