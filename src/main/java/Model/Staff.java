package Model;
import java.util.Objects;

public class Staff {

    public enum Position {
        Guard, Cleaner, Maintenance
    }

    public enum Shift {
        Morning, Afternoon, Night
    }

    private int id;
    private String firstName;
    private String lastName;
    private Position position;
    private Shift shift;
    private Building building;

    public Staff(int id, String firstName, String lastName, Position position, Shift shift, Building building) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.shift = shift;
        this.building = building;
    }
    public int getId() { return id; }

    public int setId(int id) { return this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Position getPosition() { return position; }

    public void setPosition(Position position) { this.position = position; }

    public Shift getShift() { return shift; }

    public void setShift(Shift shift) { this.shift = shift; }

    public Building getBuilding() { return building; }

    public void setBuilding(Building building) { this.building = building; }
}
