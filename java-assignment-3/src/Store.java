import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Store {
    public ArrayList<Computer> computers = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();
    public User currentUser;
    public Computer selectedComputer;

    public Store(String path) {
        computers = readComputersFromFile(path);
        users = addAllUsers();
    }

    // create all users
    private ArrayList<User> addAllUsers() {
        users.add(new User("Salesperson", "p1", "p1"));
        users.add(new User("Salesperson", "p2", "p2"));
        users.add(new User("Salesperson", "p3", "p3"));
        users.add(new User("Manager", "m1", "m1"));
        users.add(new User("Manager", "m2", "m2"));
        return users;

    }

    // load computer data
    private ArrayList<Computer> readComputersFromFile(String path) {
        ArrayList<Computer> computers = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
            String fileContent;

            while (scanner.hasNext()) {
                fileContent = scanner.nextLine();
                String[] lineContent = fileContent.split(",");
                String category = lineContent[0];
                String type = lineContent[1];
                String id = lineContent[2];
                String brand = lineContent[3];
                String cpu = lineContent[4];

                switch (lineContent[0]) {
                    case "Desktop PC":
                        int memorySize = Integer.parseInt(lineContent[5]);
                        int ssdCapacity = Integer.parseInt(lineContent[6]);
                        float price = Float.parseFloat(lineContent[7]);
                        computers.add(new Desktop(category, type, id, brand, cpu, price, memorySize, ssdCapacity));
                        break;
                    case "Laptop":
                        memorySize = Integer.parseInt(lineContent[5]);
                        ssdCapacity = Integer.parseInt(lineContent[6]);
                        float screenSize = Float.parseFloat(lineContent[7]);
                        price = Float.parseFloat(lineContent[8]);
                        computers.add(
                                new Laptop(category, type, id, brand, cpu, price, memorySize, ssdCapacity, screenSize));
                        break;
                    case "Tablet":
                        screenSize = Float.parseFloat(lineContent[5]);
                        price = Float.parseFloat(lineContent[6]);
                        computers.add(new Tablet(category, type, id, brand, cpu, price, screenSize));
                        break;
                    default:
                        System.out.println("Computer type " + lineContent[0] + "unknown");
                        break;

                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return computers;
    }

    // auth user
    public Boolean authUser(String usernameInput, String passwordInput) {
        Boolean userVerified = false;

        for (User user : users) {
            if (user.getUserName().equals(usernameInput)) {
                if (user.getPassword().equals(passwordInput)) {
                    userVerified = true;
                    this.currentUser = user;

                    break;
                }
            }
        }

        return userVerified;
    }

    // clear user session
    public void logoutUser() {
        this.currentUser = null;
    }

    // get all computer categories
    public String[] getComputerCategyList() {
        Set<String> set = new HashSet<>();
        set.add("All");

        for (Computer computer : computers) {
            set.add(computer.getCategory());
        }

        String[] computerCategoryList = set.toArray(new String[set.size()]);
        return computerCategoryList;
    }

    // get all computer types
    public String[] getComputerTypeList() {
        Set<String> set = new HashSet<>();
        set.add("All");

        for (Computer computer : computers) {
            set.add(computer.getType());
        }

        String[] computerTypeList = set.toArray(new String[set.size()]);
        return computerTypeList;
    }

    // get computers data for table
    public Object[][] getTableData() {
        Object tableData[][] = new Object[this.computers.size()][6];
        for (int i = 0; i < this.computers.size(); i++) {
            Computer computer = computers.get(i);
            tableData[i][0] = computer.getCategory();
            tableData[i][1] = computer.getType();
            tableData[i][2] = computer.getId();
            tableData[i][3] = computer.getBrand();
            tableData[i][4] = computer.getCpu();
            tableData[i][5] = Float.toString(computer.getPrice());
        }
        return tableData;
    }

    // set computer context by ID
    public void selectComputerById(Object selectedItemId) {
        for (var computer : computers) {
            if (computer.getId().equals(selectedItemId)) {
                this.selectedComputer = computer;
            }
        }
    }

    // delete computer
    public void deleteComputer(Computer computer) {
        this.computers.remove(computer);
        this.selectedComputer = null;
    }

    // add computer
    public void addComputer(Computer computer) {
        for (var c : computers) {
            if (c.getId().equals(computer.getId())) {
                throw new RuntimeException("Computer with ID " + c.getId() + " already exist");
            }
        }

        computers.add(computer);
    }
}
