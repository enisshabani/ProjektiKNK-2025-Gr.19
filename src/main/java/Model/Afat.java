package Model;
import java.time.LocalDate;


public class Afat {

    public enum Status {
        Active, Inactive
    }

    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;

    public Afat(int id, String name, LocalDate startDate, LocalDate endDate, Status status) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}
