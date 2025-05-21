package Model;

import java.time.LocalDate;

public class Archive {

    private int id;
    private String description;
    private LocalDate eventDate;
    private Student student;

    public Archive(int id, String description, LocalDate eventDate, Student student) {
        this.id = id;
        this.description = description;
        this.eventDate = eventDate;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) { this.student = student; }
}