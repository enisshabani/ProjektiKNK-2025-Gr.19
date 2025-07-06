package Model.DTO;

public class RoomDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String roomNumber;
    private String gender;
    private String roomStatus;
    private String building;
    private int capacity;
    private Integer studentId;  // Added to track student assignment

    // Constructors
    public RoomDTO() {}

    public RoomDTO(int id, String firstName, String lastName, String roomNumber,
                   String gender, String roomStatus, String building, int capacity) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomNumber = roomNumber;
        this.gender = gender;
        this.roomStatus = roomStatus;
        this.building = building;
        this.capacity = capacity;
    }

    // Enhanced constructor with studentId
    public RoomDTO(int id, String firstName, String lastName, String roomNumber,
                   String gender, String roomStatus, String building, int capacity,
                   Integer studentId) {
        this(id, firstName, lastName, roomNumber, gender, roomStatus, building, capacity);
        this.studentId = studentId;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName != null ? firstName.trim() : null;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName != null ? lastName.trim() : null;
    }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber != null ? roomNumber.trim() : null;
    }

    public String getGender() { return gender; }
    public void setGender(String gender) {
        this.gender = gender != null ? gender.trim() : null;
    }

    public String getRoomStatus() { return roomStatus; }
    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus != null ? roomStatus.trim() : null;
    }

    public String getBuilding() { return building; }
    public void setBuilding(String building) {
        this.building = building != null ? building.trim() : null;
    }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.capacity = capacity;
    }

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    // Utility methods
    public boolean isAssigned() {
        return studentId != null && studentId > 0;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", building='" + building + '\'' +
                ", status='" + roomStatus + '\'' +
                ", capacity=" + capacity +
                (isAssigned() ? ", assignedToStudentId=" + studentId : ", unassigned") +
                '}';
    }

    // Builder pattern for fluent creation
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String roomNumber;
        private String gender;
        private String roomStatus;
        private String building;
        private int capacity;
        private Integer studentId;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        // ... other builder methods for each field ...

        public Builder studentId(Integer studentId) {
            this.studentId = studentId;
            return this;
        }

        public RoomDTO build() {
            return new RoomDTO(id, firstName, lastName, roomNumber, gender,
                    roomStatus, building, capacity, studentId);
        }
    }
}