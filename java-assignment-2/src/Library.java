import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Library {
    
    public ArrayList<LibraryItem> libraryItems = new ArrayList<>();

    public Library(String path) {
        libraryItems = readLibraryItemsFromFile(path);
    }

    public void displayLibraryItems() {
        System.out.println("List of all items in the library:");
        for (LibraryItem libraryItem : libraryItems) {
            System.out.println(libraryItem);
        }
        System.out.println();
    }

    public void displayLibraryItemsSortedByRatingAndId() {
        System.out.println("List of all items in the library:");

        Collections.sort(libraryItems, Collections.reverseOrder(new RatingComparator()));

        // create array of ratings
        double[] rating = new double[libraryItems.size()];
        int i = 0;
        for (LibraryItem libraryItem:libraryItems) {
            rating[i] = libraryItem.getAverageRating();
            i++;
        }

        // check if all ratings are equal
        boolean ratingEqual = Arrays.stream(rating).distinct().count() == 1;

        // if all rating are equal then sort by ID
        if (ratingEqual == true) {
            Collections.sort(libraryItems, new IdComparator());
        }

        for (LibraryItem libraryItem:libraryItems) {
            System.out.format("Average rating: %-5s Number of reviewers: %-5s" + libraryItem + "\n", String.format("%.2f",libraryItem.getAverageRating()), libraryItem.getNumberOfReviewers());
        }

        System.out.println();
    }

    public LibraryItem getLibraryItemById(int id) {
        for (LibraryItem libraryItem:libraryItems) {
            if (Objects.equals(libraryItem.getId(), id)) {
                return libraryItem;
            }
        }
        return null;
    }

    public ArrayList<LibraryItem> getMachingLibraryItemsByTitle(String input) {
        ArrayList<LibraryItem> result = new ArrayList<>();
        int matchingLibraryItemsCounter = 0;
        for (LibraryItem libraryItem:libraryItems) {
            if (libraryItem.getTitle().toLowerCase().contains(input.toLowerCase()))  {
                result.add(matchingLibraryItemsCounter,libraryItem);
                matchingLibraryItemsCounter++;
            }
        }
        return result;
    }

    private ArrayList<LibraryItem> readLibraryItemsFromFile(String fileName) {
        ArrayList<LibraryItem> libraryItems = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            String fileContent;

            while (scanner.hasNext()) {
                fileContent = scanner.nextLine();
                String[] lineContent = fileContent.split(",");
                String type = lineContent[0];
                int id = Integer.parseInt(lineContent[1]);
                String title = lineContent[2];
                int year = Integer.parseInt(lineContent[3]);

                switch(lineContent[0]) {
                    case "Movie":
                        String director = lineContent[4];
                        libraryItems.add(new Movie(type, id, title, year, director));
                        break;
                    case "Book":
                        String author = lineContent[4];
                        int numberOfPages = Integer.parseInt(lineContent[5]);
                        libraryItems.add(new Book(type, id, title, year, author, numberOfPages));
                        break;
                    case "Journal":
                        int volume = Integer.parseInt(lineContent[4]);
                        int number = Integer.parseInt(lineContent[5]);
                        libraryItems.add(new Journal(type, id, title, year, volume, number));
                        break;
                    default:
                        System.out.println("Unknown type " + lineContent[0]);
                        break;
                }
            }
            scanner.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return libraryItems;
    }
}
