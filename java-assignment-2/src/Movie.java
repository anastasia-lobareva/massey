import java.time.format.DateTimeFormatter;

public class Movie extends LibraryItem{
    private String director;
    private int maxDaysBorrowing = 7;

    public Movie(String type, int id, String title, int year, String director) {
        super(type, id, title, year);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void displaySelectedItem() {
        System.out.println("Selected item is: ");
        System.out.println("Type: " + this.type);
        System.out.println("Title: " + this.title);
        System.out.println("ID: " + this.id);
        System.out.println("Year: " + this.year);
        System.out.println("Average rating: " + this.averageRating);
        System.out.println("Number of reviewers: " + this.numberOfReviewers);
        System.out.println("Status: " + this.status);
        if (!this.status.toLowerCase().equals("available")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            System.out.println("Due date: " + this.dueDate.format(formatter));
        }
        System.out.println("Director: " + this.director);
        System.out.println("Max number of days for borrowing: " + this.maxDaysBorrowing);
    }
}
