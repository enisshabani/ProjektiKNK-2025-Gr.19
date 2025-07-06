package Model.DTO;

public class StatistikaDTO {

    private int total;
    private long approved;
    private long pending;
    private long rejected;

    public StatistikaDTO(int total, long approved, long pending, long rejected) {
        this.total = total;
        this.approved = approved;
        this.pending = pending;
        this.rejected = rejected;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getApproved() {
        return approved;
    }

    public void setApproved(long approved) {
        this.approved = approved;
    }

    public long getPending() {
        return pending;
    }

    public void setPending(long pending) {
        this.pending = pending;
    }

    public long getRejected() {
        return rejected;
    }

    public void setRejected(long rejected) {
        this.rejected = rejected;
    }
}
