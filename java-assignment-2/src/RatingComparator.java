import java.util.Comparator;

public class RatingComparator implements Comparator <LibraryItem> {
    public int compare (LibraryItem libraryItem1, LibraryItem libraryItem2) {
        return Double.compare(libraryItem1.getAverageRating(), libraryItem2.getAverageRating());
    }
}
