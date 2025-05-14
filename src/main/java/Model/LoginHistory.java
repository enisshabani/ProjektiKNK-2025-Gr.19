package Model;

import java.time.LocalDateTime;

public class LoginHistory {

    private int id;
    private String username;
    private LocalDateTime loginTime;
    private String ipAddress;
    private boolean successful;

    public LoginHistory(int id, String username, LocalDateTime loginTime, String ipAddress, boolean successful) {
        this.id = id;
        this.username = username;
        this.loginTime = loginTime;
        this.ipAddress = ipAddress;
        this.successful = successful;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public LocalDateTime getLoginTime() { return loginTime; }

    public void setLoginTime(LocalDateTime loginTime) { this.loginTime = loginTime; }

    public String getIpAddress() { return ipAddress; }

    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public boolean isSuccessful() { return successful; }

    public void setSuccessful(boolean successful) { this.successful = successful; }
}
