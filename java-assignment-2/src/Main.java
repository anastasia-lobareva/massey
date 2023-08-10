import java.util.*;

public class Main {

    public static void main(String[] args) {

        displayInfo();

        Library library = new Library("library.txt");

        // output all library items
        library.displayLibraryItems();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMainMenu();

                LibraryItem selectedLibraryItem = null;

                String userCommand = scanner.nextLine();
    
                // exit program
                if (userCommand.equals("q")) {
                    System.exit(0);
                }
                
                // sort and display all items
                if (userCommand.equals("s")) {
                    library.displayLibraryItemsSortedByRatingAndId();
                    continue;
                }
    
                // search by ID
                if (userCommand.equals("i")) {
                    searchByIdLoop:
                    while (true) {
                        System.out.println("\nEnter ID to start search, or enter 'b' to get back to choose search method");

                        // ask for ID
                        int libraryItemId = getUserItemId(scanner);

                        if (libraryItemId == 0) {
                            break searchByIdLoop;
                        }

                        // find item by ID
                        selectedLibraryItem = library.getLibraryItemById(libraryItemId);
                        if (selectedLibraryItem == null) {
                            System.out.println("Item with ID not found, please repeat search with another ID");
                            System.out.println();
                            continue;
                        }
                
                        // print found item
                        System.out.println();
                        System.out.println(selectedLibraryItem);
                        System.out.println();
                
                        // ask for instructions
                        System.out.println("Enter 'i' to search othe item by ID, or enter any other key to select");
                        userCommand = scanner.nextLine();
    
                        // search again
                        if (userCommand.equals("i")) {
                            continue;
                        }
    
                        break searchByIdLoop;
                    }
                }
                // search by title
                else {
                    searchByTitleLoop:
                    while (true) {
                        // ask for phrase in title
                        System.out.println("Enter phrase in title to start search, or enter 'b' to go back to chose search method");
                        String libraryItemTitle = scanner.nextLine();
    
                        // validate user input
                        if (libraryItemTitle == null || libraryItemTitle.isEmpty() || libraryItemTitle.trim().isEmpty()) {
                            continue;
                        }
    
                        if (libraryItemTitle.equals("b")) {
                            break searchByTitleLoop;
                        }
    
                        // get item by user input
                        ArrayList<LibraryItem> matchingLibraryItems = library.getMachingLibraryItemsByTitle(libraryItemTitle);
    
                        // if not found
                        if (matchingLibraryItems.size() == 0) {
                            System.out.println("Title not found, try again");
                            continue;
                        }
    
                        // print
                        if (!matchingLibraryItems.isEmpty()) {
                            for (int j = 0; j < matchingLibraryItems.size(); j++) {
                                System.out.println("*" + (j + 1));
                                System.out.println(matchingLibraryItems.get(j));
                                System.out.println();
                            }
                        }
    
                        // request user to select & validate
                        try {
                            System.out.println("Enter item number to select items, or enter any other key to continue searching");
                            String selectedUserIndex = scanner.nextLine();
                            int index = Integer.parseInt(selectedUserIndex);
    
                            if (index == 0 || index > matchingLibraryItems.size()) {
                                continue;
                            }
    
                            // assign selected item
                            selectedLibraryItem = matchingLibraryItems.get(index - 1);
                        } catch (Exception e) {
                            continue;
                        }
    
                        System.out.println();
                        break searchByTitleLoop;
                    }
                }

                // restart
                if (selectedLibraryItem == null) {
                    continue;
                }

                // display selected item
                selectedLibraryItem.displaySelectedItem();
                System.out.println();

                actionLoop:
                while (true) {
                    System.out.println("Enter 'b' to borrow the item, enter 'a' to rate the item, or any other key to restart");
                    String userAction = scanner.nextLine();

                    // borrow item
                    if (userAction.equals("b")) {
                        System.out.println();
                        selectedLibraryItem.borrowItem();
                    }

                    // return item
                    if (selectedLibraryItem.status.equals("on loan")) {
                        System.out.println("\nEnter 'r' to return the item, enter 'a' to rate the item, or any other key to restart");
                        userAction = scanner.nextLine();

                        if (userAction.equals("r")) {
                            System.out.println();
                            selectedLibraryItem.returnItem();
                            System.out.println();

                            continue;
                        }
                    }
    
                    // rate item
                    if (userAction.equals("a")) {
                        int userRating = getUserRating(scanner);
                        selectedLibraryItem.rateItem(userRating);
                        System.out.println();

                        continue;
                    }
                    
                    // restart
                    System.out.println();
                    break actionLoop;
                }
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("Enter 'q' to quit,\n" +
        "or enter 's' to sort (first by average rating and then by id) and display all items, \n" +
        "or enter 'i' to search by ID, \n" +
        "or enter any other key to search by phrase in title");
    }

    private static void displayInfo() {
        System.out.println("**************************************");
        System.out.println("Assignment 2 Semester 1 2023");
        System.out.println("Submitted by: Lobareva, Ana 23011712");
        System.out.println("**************************************");
        System.out.println();
    }

    public static int getUserItemId(Scanner scanner) {
        while (true) {
            String userCommand = scanner.nextLine();

            // back to main menu
            if (userCommand.equals("b")) {
                return 0;
            }

            try {
                //check if three digit integer entered
                int id = Integer.parseInt(userCommand);
                if (id < 100 || id > 999) {
                    System.out.println("Please enter three digit ID number");
                    System.out.println();
                    continue;
                }
                return id;

            } catch (NumberFormatException e) {
                System.out.println("Incorrect ID, try again, or enter 'b' to get back to choose search method.\n");
            }
        }
    }

    private static int getUserRating(Scanner scanner) {
        while (true) {
            System.out.println("\nPlease enter your rating (0-10)");
            try {
                String rateAction = scanner.nextLine();
                int rate = Integer.parseInt(rateAction);
                if (rate < 0 || rate > 10) {
                    continue;
                }
                return rate;
            } catch (Exception e) {
                continue;
            }
        }
    }
}