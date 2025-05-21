package Model;

import java.time.LocalDate;

public class DamageReport {

    public enum Status {
        Ne_Pritje,
        E_Riparuar
    }

    private int id;
    private String description;
    private LocalDate reportDate;
    private String reporter; // Could be a Student or Staff name
    private Status status;

    public DamageReport(int id, String description, LocalDate reportDate, String reporter, Status status) {
        this.id = id;
        this.description = description;
        this.reportDate = reportDate;
        this.reporter = reporter;
        this.status = status;
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

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) { this.status =  status; }
}