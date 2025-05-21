package Model;

import java.time.LocalDate;

public class RoomAssignment {

    public enum AssignmentStatus {
        Aktive,
        Perfunduar
    }

    private int id;
    private Student student;
    private Room room;
    private LocalDate entryDate;
    private LocalDate exitDate;
    private AssignmentStatus status;

    public RoomAssignment(int id, Student student, Room room, LocalDate entryDate, LocalDate exitDate,
            AssignmentStatus status) {
        this.id = id;
        this.student = student;
        this.room = room;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public AssignmentStatus getStatus() {
        return status;
    }

    public void setStatus(AssignmentStatus status) { this.status = status; }
}