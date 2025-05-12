package Model;
import java.time.LocalDate;

public class Application {
    public enum Status {
        In_Process, Accepted, Rejected
    }
    private LocalDate applicationDate;
    private Student student;
    private Status status;
    private LocalDate deadline;

    public Application(LocalDate applicationDate, Student student, Status status, LocalDate deadline) {
        this.applicationDate = applicationDate;
        this.student = student;
        this.status = status;
        this.deadline = deadline;
    }

    public LocalDate getApplicationDate() { return applicationDate; }

    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public LocalDate getDeadline() { return deadline; }

    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
}
