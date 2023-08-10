public class Main {
    static String myMajor = "CS";
    static long myId = 23011712;

    public static void main(String[] args) {

        School school = new School("The School of Mathematical and Computational Sciences");

        school.addMajor(new Major("Computer Science", "CS"));
        school.addMajor(new Major("Information Technology", "IT"));
        school.addMajor(new Major("Information Systems", "IS"));
        school.addMajor(new Major("Software Engineering", "SE"));
        school.addMajor(new Major("Data Science", "DS"));

        school.addLecturer(new Lecturer(1105236, "Amy", "Sheffield", "PN"));
        school.addLecturer(new Lecturer(1235894, "Victoria", "Jensen", "PN"));
        school.addLecturer(new Lecturer(7225669, "James", "Lee", "PN"));
        school.addLecturer(new Lecturer(1328991, "Colin", "Delmont", "PN"));
        school.addLecturer(new Lecturer(1562347, "Thomas", "Becker", "Auckland"));
        school.addLecturer(new Lecturer(5664789, "Steven", "Hobbs", "Auckland"));
        school.addLecturer(new Lecturer(3658947, "Andrew", "Jackson", "Auckland"));
        school.addLecturer(new Lecturer(6332698, "Jonathon", "Wood", "Auckland"));
        school.addLecturer(new Lecturer(myId,"Ana", "Lobareva", "PN"));

        Paper itpPaper = new Paper(158100, "Information Technology Principles");
        itpPaper.addAssessment(new Assessment(70, 30, 0));
        itpPaper.addMajor(school.getMajorByShortName("IT"));
        itpPaper.addMajor(school.getMajorByShortName("IS"));
        school.addPaper(itpPaper);

        Paper wbfPaper = new Paper(158120, "Web-based IT Fundamentals");
        wbfPaper.addAssessment(new Assessment(60, 40, 0));
        wbfPaper.addMajor(school.getMajorByShortName("IT"));
        wbfPaper.addMajor(school.getMajorByShortName("IS"));
        school.addPaper(wbfPaper);

        Paper appPaper = new Paper(159101, "Applied Programming");
        appPaper.addAssessment(new Assessment(50, 50, 0));
        appPaper.addMajor(school.getMajorByShortName("IT"));
        appPaper.addMajor(school.getMajorByShortName("IS"));
        appPaper.addMajor(school.getMajorByShortName("CS"));
        appPaper.addMajor(school.getMajorByShortName("DS"));
        appPaper.addMajor(school.getMajorByShortName("SE"));
        school.addPaper(appPaper);

        Paper adsPaper = new Paper(159201, "Algorithms and Data Structures");
        adsPaper.addAssessment(new Assessment(40, 20, 40));
        adsPaper.addMajor(school.getMajorByShortName("IS"));
        adsPaper.addMajor(school.getMajorByShortName("CS"));
        adsPaper.addMajor(school.getMajorByShortName("DS"));
        adsPaper.addMajor(school.getMajorByShortName("SE"));
        school.addPaper(adsPaper);

        Paper oopPaper = new Paper(159234, "Object-Oriented Programming");
        oopPaper.addAssessment(new Assessment(50, 10, 40));
        oopPaper.addMajor(school.getMajorByShortName("CS"));
        oopPaper.addMajor(school.getMajorByShortName("SE"));
        school.addPaper(oopPaper);

        Paper dadPaper = new Paper(158337, "Database Development");
        dadPaper.addAssessment(new Assessment(60, 0, 40));
        dadPaper.addMajor(school.getMajorByShortName("IT"));
        dadPaper.addMajor(school.getMajorByShortName("DS"));
        dadPaper.addMajor(school.getMajorByShortName("SE"));
        school.addPaper(dadPaper);

        Paper pisPaper = new Paper(158345, "Professionalism in the Information Sciences");
        pisPaper.addAssessment(new Assessment(100, 0, 0));
        pisPaper.addMajor(school.getMajorByShortName("IT"));
        pisPaper.addMajor(school.getMajorByShortName("IS"));
        pisPaper.addMajor(school.getMajorByShortName("CS"));
        pisPaper.addMajor(school.getMajorByShortName("DS"));
        pisPaper.addMajor(school.getMajorByShortName("SE"));
        school.addPaper(pisPaper);

        for (int i = 0; i < school.getPapers().length; i++) {

            Paper paper = school.getPapers()[i];

            if (paper != null) {

                Lecturer distanceLecturer = school.getAvailableLecturer();
                Offering distanceOffering = new Offering("Distance", distanceLecturer, paper);
                distanceLecturer.addAssignment();
                school.addOffering(distanceOffering);

                Lecturer aucklandLecturer = school.getAvailableLecturerByLocation("Auckland");
                Offering aucklandOffering = new Offering("Auckland", aucklandLecturer, paper);
                aucklandLecturer.addAssignment();
                school.addOffering(aucklandOffering);

                Lecturer pnLecturer = school.getAvailableLecturerByLocation("PN");
                Offering pnOffering = new Offering("PN", pnLecturer, paper);
                pnLecturer.addAssignment();
                school.addOffering(pnOffering);
            }
        }

        Offering[] myOfferings = school.getOfferingsByLeacturerId(myId);

        Lecturer lecturer = school.getPaperLecturerByLocation(159234, "Auckland");

        displayInfo();
        displayTask1(school);
        displayTask2(school.getPapers());
        displayTask3(school.getPapers());
        displayTask4(school.getPapers());
        displayTask5(school.getPapers());
        displayTask6(school.getOfferings());
        displayTask7(myOfferings);
        displayTask8(lecturer);
    }

    private static void displayInfo() {
        System.out.println("**************************************");
        System.out.println("Assignment 1, 159.234 Semester 1 2023");
        System.out.println("Submitted by: Ana Lobareva, ID: " + myId);
        System.out.println("My Major at Massey: Computer Science");
        System.out.println("**************************************");
        System.out.println();
    }

    private static void displayTask1(School school) {
        System.out.println("-------------- Task 1 ----------------------");
        System.out.println("School Full Name: " + school.getName());
        System.out.println();
    }

    private static void displayTask2(Paper[] papers) {
        System.out.println("-------------- Task 2 ----------------------");
        System.out.println("All papers details:");

        for (int i = 0; i < papers.length; i++) {

            Paper paper = papers[i];

            if (paper != null)
            {
                System.out.print(paper.getNumber() + "\t" + paper.getName() + " ( ");

                Major[] majors = paper.getMajors();

                for (int j = 0; j < paper.majorsCount; j++){

                    Major major = majors[j];

                    System.out.print(major.getShortName() + " ");
                }
                System.out.println(")");
            }
        }
        System.out.println();
    }

    private static void displayTask3(Paper[] papers) {
        System.out.println("-------------- Task 3 ----------------------");
        System.out.println("Papers that belong to Major " + myMajor + ":");

        int papersCounter = 0;

        for (int i = 0; i < papers.length; i++) {

            Paper paper = papers[i];

            if (paper != null) {

                Major[] majors = paper.getMajors();

                for (int j = 0; j < paper.majorsCount; j++) {

                    Major major = majors[j];

                    if (major.getShortName() == myMajor) {
                        System.out.println("Paper - " + paper.getNumber() + " " + paper.getName());
                        papersCounter++;
                    }
                }
            }
        }
        System.out.println("Total matching papers in specified Major - " + myMajor + ": " + papersCounter);
        System.out.println();
    }

    private static void displayTask4(Paper[] papers) {
        System.out.println("-------------- Task 4 ----------------------");
        System.out.println("Papers that have exam:");

        int examCounter = 0;

        for (int i = 0; i < papers.length; i++){

            Paper paper = papers[i];

            if (paper != null) {

                Assessment assessment = paper.getAssessment();

                if (assessment.getExam() != 0) {
                    System.out.println("Paper - " + paper.getNumber() + " " + paper.getName());
                    examCounter++;
                }
            }
        }
        System.out.println("Total number of papers that have exam: " + examCounter);
        System.out.println();
    }

    private static void displayTask5(Paper[] papers) {
        System.out.println("-------------- Task 5 ----------------------");
        System.out.println("Papers that their assignments weight higher than 50.0%: ");

        int assignmentCounter = 0;

        for (int i = 0; i < papers.length; i++){

            Paper paper = papers[i];

            if (paper != null) {

                Assessment assessment = paper.getAssessment();

                if (assessment.getAssignment() > 50) {
                    System.out.println("Paper - " + paper.getNumber() + " " + paper.getName());
                    assignmentCounter++;
                }
            }
        }
        System.out.println("Total number of papers that have assignments weighted more than 50%: " + assignmentCounter);
        System.out.println();
    }

    private static void displayTask6(Offering[] offerings) {
        System.out.println("-------------- Task 6 ----------------------");
        System.out.println("Papers offering details:");

        for (int i = 0; i < offerings.length; i++) {

            Offering offering = offerings[i];

            if (offering != null) {

                System.out.println(offering);
            }
        }
        System.out.println();
    }

    private static void displayTask7(Offering[] offerings) {
        System.out.println("-------------- Task 7 ----------------------");
        System.out.println("The paper offerings I teach:");

        int myOfferingCount = 0;

        for (int i = 0; i < offerings.length; i++) {

            Offering offering = offerings[i];

            if (offering != null) {

                System.out.println(offering.toDetailedString());
                myOfferingCount++;
            }
        }
        System.out.format("I am teaching %s paper(s)", myOfferingCount);
        System.out.println();
        System.out.println();
    }

    private static void displayTask8(Lecturer lecturer){
        System.out.println("-------------- Task 8 ----------------------");
        System.out.println("The lecturer of Auckland offering of 159234:");
        System.out.println("Lecturer's name: " + lecturer.getFullName());
        System.out.format("This lecturer is teaching %s paper(s)", lecturer.getAssignments());
        System.out.println();
    }
}