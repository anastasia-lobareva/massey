public class Tablet extends Computer {
    private float screenSize;

    public Tablet(String category, String type, String id, String brand, String cpu, float price, float screenSize) {
        super(category, type, id, brand, cpu, price);
        this.screenSize = screenSize;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public int getSsdCapacity() {
        return 0;
    }

    public int getMemorySize() {
        return 0;
    }

    public void Update(String category, String type, String brand, String cpu, float price, int memorySize, int ssdCapacity, float screenSize) {
        this.category = category;
        this.type = type;
        this.brand = brand;
        this.cpu = cpu;
        this.price = price;
        this.screenSize = screenSize;
    }
}
