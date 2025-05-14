package Model;
import java.time.LocalDate;


public class StatusiRegjistrimit {
    public enum Status {
        Active, Inactive, Removed
    }

    private int id;
    private Student student;
    private Status status;
    private LocalDate date;

    public StatusiRegjistrimit(int id, Student student, Status status, LocalDate date) {
        this.id = id;
        this.student = student;
        this.status = status;
        this.date = date;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }
}
