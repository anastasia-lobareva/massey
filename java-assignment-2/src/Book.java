import java.time.format.DateTimeFormatter;

public class Book extends LibraryItem{
    private String author;
    private int numberOfPages;
    private int maxDaysBorrowing = 28;

    public Book(String type, int id, String title, int year, String author, int numberOfPages) {
        super(type, id, title, year);
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void displaySelectedItem() {
        System.out.println("Selected item is:");
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
        System.out.println("Author: " + this.author);
        System.out.println("Number of pages: " + this.numberOfPages);
        System.out.println("Max number of days for borrowing: " + this.maxDaysBorrowing);
    }
}
