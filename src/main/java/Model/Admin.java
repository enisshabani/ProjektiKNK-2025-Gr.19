package Model;
import java.util.Objects;

public class Admin {

    public enum Role {
        Superadmin, Secretary
    }

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String salt;
    private Role role;

    public Admin(String firstName, String lastName, String email, String passwordHash, String salt, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.role = role;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }

    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getSalt() { return salt; }

    public void setSalt(String salt) { this.salt = salt; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }
}
