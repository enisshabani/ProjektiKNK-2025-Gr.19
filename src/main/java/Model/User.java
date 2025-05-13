package Model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String currentAccountSatus;

    public User(int id, String username, String password, String role, String currentAccountSatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.currentAccountSatus = currentAccountSatus;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public String getCurrentAccountSatus() { return currentAccountSatus; }

    public void setCurrentAccountSatus(String currentAccountSatus) { this.currentAccountSatus = currentAccountSatus; }
}
