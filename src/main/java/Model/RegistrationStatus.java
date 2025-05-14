package Model;

import java.time.LocalDate;

public class RegistrationStatus {

    public enum Status {
        Aktiv,
        Joaktiv,
        I_Larguar
    }

    private int id;
    private Student student;
    private Status status;
    private LocalDate changeDate;

    public RegistrationStatus(int id, Student student, Status status, LocalDate changeDate) {
        this.id = id;
        this.student = student;
        this.status = status;
        this.changeDate = changeDate;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public LocalDate getChangeDate() { return changeDate; }

    public void setChangeDate(LocalDate changeDate) { this.changeDate = changeDate; }
}
