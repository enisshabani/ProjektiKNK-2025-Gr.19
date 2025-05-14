package Model;
import java.time.LocalDate;


public class Njoftim {
    public enum TargetGroup {
        Student, Staff, All
    }

    private int id;
    private String title;
    private String content;
    private LocalDate date;
    private Admin sender;
    private TargetGroup targetGroup;

    public Njoftim(int id, String title, String content, LocalDate date, Admin sender, TargetGroup targetGroup) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.sender = sender;
        this.targetGroup = targetGroup;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public Admin getSender() { return sender; }

    public void setSender(Admin sender) { this.sender = sender; }

    public TargetGroup getTargetGroup() { return targetGroup; }

    public void setTargetGroup(TargetGroup targetGroup) { this.targetGroup = targetGroup; }
}
