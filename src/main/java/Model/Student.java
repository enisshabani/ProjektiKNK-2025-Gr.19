package model;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String school;
    private int yearOfStudy;
    private String cityOfOrigin;
    private String salt;
    private String hashedPassword;
    private String status;

    public Student(int id, String firstName, String lastName, String email, String gender,
                   String school, int yearOfStudy, String cityOfOrigin, String salt,
                   String hashedPassword, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.school = school;
        this.yearOfStudy = yearOfStudy;
        this.cityOfOrigin = cityOfOrigin;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.status = status;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public int getYearOfStudy() { return yearOfStudy; }

    public void setYearOfStudy(int yearOfStudy) { this.yearOfStudy = yearOfStudy; }

    public String getCityOfOrigin() { return cityOfOrigin; }

    public void setCityOfOrigin(String cityOfOrigin) { this.cityOfOrigin = cityOfOrigin; }

    public String getSalt() { return salt; }

    public void setSalt(String salt) { this.salt = salt; }

    public String getHashedPassword() { return hashedPassword; }

    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
