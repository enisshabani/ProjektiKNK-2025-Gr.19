package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Room {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty roomNumber;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty roomStatus;
    private final SimpleStringProperty building;
    private final SimpleIntegerProperty capacity;

    public Room(int id, String firstName, String lastName, String roomNumber,
                String gender, String roomStatus, String building, int capacity) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.gender = new SimpleStringProperty(gender);
        this.roomStatus = new SimpleStringProperty(roomStatus);
        this.building = new SimpleStringProperty(building);
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getRoomNumber() {
        return roomNumber.get();
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getRoomStatus() {
        return roomStatus.get();
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus.set(roomStatus);
    }

    public String getBuilding() {
        return building.get();
    }

    public void setBuilding(String building) {
        this.building.set(building);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }
}