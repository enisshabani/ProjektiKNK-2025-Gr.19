package Controller;

import javafx.beans.property.*;

public class Room {
    private final IntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty roomNumber;
    private final StringProperty gender;
    private final StringProperty roomStatus;
    private final IntegerProperty buildingId;
    private final IntegerProperty capacity;

    public Room(int id, String firstName, String lastName, String roomNumber, String gender, String roomStatus, int buildingId, int capacity) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.gender = new SimpleStringProperty(gender);
        this.roomStatus = new SimpleStringProperty(roomStatus);
        this.buildingId = new SimpleIntegerProperty(buildingId);
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    public IntegerProperty idProperty() { return id; }
    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty lastNameProperty() { return lastName; }
    public StringProperty roomNumberProperty() { return roomNumber; }
    public StringProperty genderProperty() { return gender; }
    public StringProperty roomStatusProperty() { return roomStatus; }
    public IntegerProperty buildingIdProperty() { return buildingId; }
    public IntegerProperty capacityProperty() { return capacity; }

    public String getRoomNumber() { return roomNumber.get(); }
}
