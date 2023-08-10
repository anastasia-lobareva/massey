public class Main {
    public static void main(String[] args) throws Exception {

        Store store = new Store("computers.txt");

        new StoreWindow(store);
    }
}
