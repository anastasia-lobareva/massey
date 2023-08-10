public class Laptop extends Computer {
    private int memorySize;
    private int ssdCapacity;
    private float screenSize;

    public Laptop(String category, String type, String id, String brand, String cpu, float price, int memorySize, int ssdCapacity, float screenSize) {
        super(category, type, id, brand, cpu, price);
        this.memorySize = memorySize;
        this.ssdCapacity = ssdCapacity;
        this.screenSize = screenSize;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public int getSsdCapacity() {
        return ssdCapacity;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void Update(String category, String type, String brand, String cpu, float price, int memorySize, int ssdCapacity, float screenSize) {
        this.category = category;
        this.type = type;
        this.brand = brand;
        this.cpu = cpu;
        this.price = price;
        this.memorySize = memorySize;
        this.ssdCapacity = ssdCapacity;
        this.screenSize = screenSize;
    }
}
