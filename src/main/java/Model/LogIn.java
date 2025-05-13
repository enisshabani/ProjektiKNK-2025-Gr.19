package Model;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LogIn {
     private int id;
     private String user;
     private LocalDate date;
     private LocalDateTime time;
     private int IPAddress;
     private String isLoginSuccsesful;

    public LogIn(int id, String user, LocalDateTime time, LocalDate date, int IPAddress, String isLoginSuccsesful) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.date = date;
        this.IPAddress = IPAddress;
        this.isLoginSuccsesful = isLoginSuccsesful;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public LocalDateTime getTime() { return time; }

    public void setTime(LocalDateTime time) { this.time = time; }

    public int getIPAddress() { return IPAddress; }

    public void setIPAddress(int IPAddress) { this.IPAddress = IPAddress; }

    public String getIsLoginSuccsesful() { return isLoginSuccsesful; }

    public void setIsLoginSuccsesful(String isLoginSuccsesful) { this.isLoginSuccsesful = isLoginSuccsesful; }
}
