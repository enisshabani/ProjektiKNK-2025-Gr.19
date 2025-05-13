package Model;

public class Term {
    private int id;
    private String name;
    private int openingDate;
    private int closingDate;
    private String status;

    public Term(int id, String name, int openingDate, int closingDate, String status) {
        this.id = id;
        this.name = name;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.status = status;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getOpeningDate() { return openingDate; }

    public void setOpeningDate(int openingDate) { this.openingDate = openingDate; }

    public int getClosingDate() { return closingDate; }

    public void setClosingDate(int closingDate) { this.closingDate = closingDate; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
