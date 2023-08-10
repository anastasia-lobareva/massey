public class Assessment {
    private int assignment;
    private int test;
    private int exam;

    public Assessment(int assignment, int test, int exam) {
        this.assignment = assignment;
        this.test = test;
        this.exam = exam;
    }

    public int getAssignment() {
        return assignment;
    }

    public int getExam() {
        return exam;
    }
}
