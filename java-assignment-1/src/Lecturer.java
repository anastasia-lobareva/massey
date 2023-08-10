public class Lecturer {
    private long id;
    private String firstName;
    private String lastName;
    private String campus;
    private int assignments = 0;

    public Lecturer(long id, String firstName, String lastName, String campus) {
        this.id = id;;
        this.firstName = firstName;
        this.lastName = lastName;
        this.campus = campus;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public long getId() {
        return this.id;
    }

    public String getCampus() {
        return this.campus;
    }

    public void addAssignment() {
        this.assignments++;
    }

    public int getAssignments() {
        return this.assignments;
    }

    public boolean isAvailable() {
        return this.assignments < 4;
    }
}
