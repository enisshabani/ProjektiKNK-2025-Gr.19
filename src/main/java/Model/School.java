package Model;

public class School {

    public enum Type {
        Gjimnaz,
        Fakultet
    }

    private int id;
    private String name;
    private String city;
    private Type type;

    public School(int id, String name, String city, Type type) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.type = type;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }
}
