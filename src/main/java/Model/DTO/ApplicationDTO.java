package Model.DTO;

import Model.Application.Status;
import java.time.LocalDate;

public class ApplicationDTO {
    private LocalDate applicationDate;
    private int studentId;
    private String studentName;
    private Status status;
    private LocalDate deadline;

    public ApplicationDTO(LocalDate applicationDate, int studentId, String studentName, Status status, LocalDate deadline) {
        this.applicationDate = applicationDate;
        this.studentId = studentId;
        this.studentName = studentName;
        this.status = status;
        this.deadline = deadline;
    }

    public LocalDate getApplicationDate() { return applicationDate; }
    public int getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public Status getStatus() { return status; }
    public LocalDate getDeadline() { return deadline; }
}
