public class Offering {
    private String location;
    private Lecturer lecturer;
    private Paper paper;

    public Offering(String location, Lecturer lecturer, Paper paper) {

        this.location = location;
        this.lecturer = lecturer;
        this.paper = paper;
    }

    public long getLecturerId() {
        return this.lecturer.getId();
    }

    public long getPaperNumber() {
        return this.paper.getNumber();
    }

    public String getLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-10s %s", this.paper.getNumber(), this.location, this.lecturer.getFullName());
    }

    public String toDetailedString() {
        return String.format("Paper offering - %-8s %-10s Lecturer: %s", this.paper.getNumber(), this.location, this.lecturer.getFullName());
    }
}
