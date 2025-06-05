package DTO;

public class SignUpDTO {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String university;
    private String universityYear;
    private double averageGrade;
    private String gender;

    public SignUpDTO(String name, String lastName, String email, String password,
                     String university, String universityYear, double averageGrade, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.university = university;
        this.universityYear = universityYear;
        this.averageGrade = averageGrade;
        this.gender = gender;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUniversityYear() {
        return universityYear;
    }

    public void setUniversityYear(String universityYear) {
        this.universityYear = universityYear;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}