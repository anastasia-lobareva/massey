public class Desktop extends Computer {
    private int memorySize;
    private int ssdCapacity;

    public Desktop(String category, String type, String id, String brand, String cpu, float price, int memorySize, int ssdCapacity) {
        super(category, type, id, brand, cpu, price);
        this.memorySize = memorySize;
        this.ssdCapacity = ssdCapacity;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public int getSsdCapacity() {
        return ssdCapacity;
    }

    public float getScreenSize() {
        return 0;
    }

    public void Update(String category, String type, String brand, String cpu, float price, int memorySize, int ssdCapacity, float screenSize) {
        this.category = category;
        this.type = type;
        this.brand = brand;
        this.cpu = cpu;
        this.price = price;
        this.memorySize = memorySize;
        this.ssdCapacity = ssdCapacity;
    }
}
