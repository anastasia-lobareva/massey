import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract public class LibraryItem {

    protected String type;
    protected int id;
    protected String title;
    protected int year;
    protected double averageRating = 0.00;
    protected int numberOfReviewers = 0;
    protected String status = "available";
    protected LocalDate dueDate;
    protected int maxDaysBorrowing;

    public LibraryItem(String type, int id, String title, int year){
        this.type = type;
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getNumberOfReviewers() {
        return numberOfReviewers;
    }

    public int getMaxDaysBorrowing() {
        return maxDaysBorrowing;
    }
    
    public String toString() {
        return String.format("ID: %-5s Type: %-10s Title: %-10s", id, type, title);
    }

    public void borrowItem() {
        if (this.status.equals("on loan")) {
            System.out.println("This item is already borrowed");
            return;
        }

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        this.status = "on loan";
        this.dueDate = currentDate.plusDays(this.maxDaysBorrowing);

        System.out.println("This item's due date is " + this.dueDate.format(formatter) + "\n");
        this.displaySelectedItem();
    }

    public void returnItem() {
        this.status = "available";
        this.dueDate = null;

        System.out.println("This item is returned\n");
        this.displaySelectedItem();
    }

    public void rateItem(int rating) {
        this.numberOfReviewers++;
        this.averageRating = (this.averageRating + rating) / this.numberOfReviewers;

        System.out.println("This item's new average rating is " + this.averageRating + "\n");
        this.displaySelectedItem();
    }

    public abstract void displaySelectedItem();
}
