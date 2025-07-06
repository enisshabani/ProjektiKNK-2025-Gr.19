package Model.DTO;

import java.time.LocalDate;

public class AplikimetDTO {
    private int id;
    private String studentName;
    private LocalDate applicationDate;
    private String status;

    public AplikimetDTO(int id, String studentName, LocalDate applicationDate, String status) {
        this.id = id;
        this.studentName = studentName;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
