package Model;

import java.time.LocalDate;

public class Payment {

    public enum Status {
        E_Kryer,
        Jo_E_Kryer
    }

    private int id;
    private Student student;
    private LocalDate paymentDate;
    private double amount;
    private String month;
    private Status status;

    public Payment(int id, Student student, LocalDate paymentDate, double amount, String month, Status status) {
        this.id = id;
        this.student = student;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.month = month;
        this.status = status;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public LocalDate getPaymentDate() { return paymentDate; }

    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public String getMonth() { return month; }

    public void setMonth(String month) { this.month = month; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}