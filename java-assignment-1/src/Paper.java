public class Paper {
    private long number;
    private String name;
    private Assessment assessment;
    private Major[] majors = new Major[5];
    int majorsCount = 0;

    public Paper(long number, String name) {
        this.number = number;
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public Major[] getMajors() {
        return majors;
    }

    public void addMajor(Major major) {
        if (majorsCount < majors.length)
        {
            majors[majorsCount] = major;
            majorsCount++;
        }
        else
        {
            System.out.println("Cannot add more modules");
        }
    }

    public void addAssessment(Assessment assessment) {
        this.assessment = assessment;
    }
}
