import java.time.format.DateTimeFormatter;

public class Journal extends LibraryItem{
    private int volume;
    private int number;

    public Journal(String type, int id, String title, int year, int volume, int number) {
        super(type, id, title, year);
        this.volume = volume;
        this.number = number;
    }

    public int getVolume() {
        return volume;
    }

    public int getNumber() {
        return number;
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
        System.out.println("Volume: " + this.volume);
        System.out.println("Number: " + this.number);
        System.out.println("Max number of days for borrowing: " + this.maxDaysBorrowing);
    }
}
