package Model.DTO;

public class ForgotPasswordDTO {
    private String email;
    private int confirmationCode;
    private String newPassword;
    private String hashedPassword;

    public ForgotPasswordDTO() {}

    public ForgotPasswordDTO(String email, int confirmationCode, String newPassword) {
        this.email = email;
        this.confirmationCode = confirmationCode;
        this.newPassword = newPassword;
    }

    // Getters and Setters
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
