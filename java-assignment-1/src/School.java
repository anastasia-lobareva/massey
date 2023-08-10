public class School {
    private String name;
    private Major[] majors = new Major[5];
    private Lecturer[] lecturers = new Lecturer[9];
    private Paper[] papers = new Paper[7];
    private Offering[] offerings = new Offering[21];
    private int majorsCount = 0;
    private int lecturersCount = 0;
    private int papersCount = 0;
    private int offeringsCount = 0;

    public School(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addMajor(Major major) {
        if (majorsCount < majors.length)
        {
            majors[majorsCount] = major;
            majorsCount++;
        }
        else
        {
            System.out.println("Cannot add more majors");
        }
    }

    public void addLecturer(Lecturer lecturer) {
        if (lecturersCount < lecturers.length)
        {
            lecturers[lecturersCount] = lecturer;
            lecturersCount++;
        }
        else
        {
            System.out.println("Cannot add more lecturers");
        }
    }

    public void addPaper(Paper paper) {
        if (papersCount < papers.length)
        {
            papers[papersCount] = paper;
            papersCount++;
        }
        else
        {
            System.out.println("Cannot add more papers");
        }
    }

    public void addOffering(Offering offering) {
        if (offeringsCount < offerings.length) {
                offerings[offeringsCount] = offering;
                offeringsCount++;
        }
        else
        {
            System.out.println("Cannot add more offerings");
        }
    }

    public Paper[] getPapers() {
        return this.papers;
    }

    public Offering[] getOfferings() {
        return this.offerings;
    }

    public Major getMajorByShortName(String name) {
        for (int i = 0; i < this.majorsCount; i++) {
            if (this.majors[i].getShortName() == name) {
                return this.majors[i];
            }
        }
        return null;
    }

    public Lecturer getAvailableLecturer() {

        Lecturer[] availableLecturers = new Lecturer[this.lecturersCount];

        int availableLecturersCount = 0;

        for (int i = 0; i < this.lecturersCount; i++) {

            Lecturer lecturer = this.lecturers[i];

            if (lecturer != null) {

                if (lecturer.isAvailable()) {

                    availableLecturers[availableLecturersCount] = lecturer;
                    availableLecturersCount++;
                }
            }
        }

        int selectedLecturerIndex = (int)(Math.random()*availableLecturersCount);

        return availableLecturers[selectedLecturerIndex];
    }

    public Offering[] getOfferingsByLeacturerId(long id) {

        Offering[] lecturerOfferings = new Offering[this.offeringsCount];

        int lecturerOfferingsCount = 0;

        for (int i = 0; i < this.offeringsCount; i++) {

            Offering offering = this.offerings[i];

            if (offering != null) {

                if (offering.getLecturerId() == id) {

                    lecturerOfferings[lecturerOfferingsCount] = offering;
                    lecturerOfferingsCount++;
                }
            }
        }

        return lecturerOfferings;
    }

    public Lecturer getPaperLecturerByLocation(long paperNumber, String location) {

        for (int i = 0; i < this.offeringsCount; i++) {

            Offering offering = this.offerings[i];

            if (offering != null) {

                if (offering.getPaperNumber() == paperNumber && offering.getLocation() == location) {

                    long lecturerId = offering.getLecturerId();

                    for (int j = 0; j < this.lecturersCount; j++) {

                        Lecturer lecturer = this.lecturers[j];

                        if (lecturer != null) {

                            if (lecturer.getId() == lecturerId) {

                                return lecturer;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    public Lecturer getAvailableLecturerByLocation(String location) {

        Lecturer[] availableLocationLecturers = new Lecturer[this.lecturersCount];

        int availableLocationLecturersCount = 0;

        for (int i = 0; i < this.lecturersCount; i++) {

            Lecturer lecturer = this.lecturers[i];

            if (lecturer != null) {

                if (lecturer.getCampus() == location && lecturer.isAvailable()) {

                    availableLocationLecturers[availableLocationLecturersCount] = lecturer;
                    availableLocationLecturersCount++;
                }
            }
        }

        int selectedLecturerIndex = (int)(Math.random()*availableLocationLecturersCount);

        return availableLocationLecturers[selectedLecturerIndex];
    }
}
