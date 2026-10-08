package example.com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student() {
        super();
        this.active = true;
    }

    // Constructor Overloading 1: Without email
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, "N/A"); // Calling superclass constructor
        this.batch = batch;
        this.active = true;
    }

    // Constructor Overloading 2: With email & active status
    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email); // Calling superclass constructor
        this.batch = batch;
        this.active = active;
    }

    // Method Overriding (Polymorphism)
    @Override
    public String getDisplayName() {
        return "[Student] " + super.getDisplayName() + " (StudentId: " + getId() + ", Batch: " + batch + ", Active: " + active + ")";
    }

    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Student [ID=" + getId() + ", Name=" + getFirstName() + " " + getLastName() +
                ", Email=" + getEmail() + ", Batch=" + batch + ", Active=" + active + "]";
    }
}