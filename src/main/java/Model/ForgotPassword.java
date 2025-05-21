package Model;

public class ForgotPassword {
    private String email;
    private int confirmationCode;
    private String password;

    public ForgotPassword(String email, int confirmationCode, String password) {
        this.email = email;
        this.confirmationCode = confirmationCode;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(int confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
