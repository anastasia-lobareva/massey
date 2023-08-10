import java.util.Comparator;

public class IdComparator implements Comparator <LibraryItem> {
    public int compare (LibraryItem libraryItem1, LibraryItem libraryItem2) {
        return (libraryItem1.getId() - libraryItem2.getId());
    }
}
