package Model;

public class Room {
    private int roomNumber;
    private int capacity;
    private String genderAllowed;
    private String status;
    private int buildingId;

    public Room(int roomNumber, int capacity, String genderAllowed, String status, int buildingId) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.genderAllowed = genderAllowed;
        this.status = status;
        this.buildingId = buildingId;
    }

    public int getRoomNumber() { return roomNumber; }

    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public int getCapacity() { return capacity; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getGenderAllowed() { return genderAllowed; }

    public void setGenderAllowed(String genderAllowed) { this.genderAllowed = genderAllowed; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public int getBuildingId() { return buildingId; }

    public void setBuildingId(int buildingId) { this.buildingId = buildingId; }
}



