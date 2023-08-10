abstract public class Computer {
    protected String category;
    protected String type;
    protected String id;
    protected String brand;
    protected String cpu;
    protected float price;

    public Computer(String categoty, String type, String id, String brand, String cpu, float price) {
        this.category = categoty;
        this.type = type;
        this.id = id;
        this.brand = brand;
        this.cpu = cpu;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }
    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getCpu() {
        return cpu;
    }

    public float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public abstract int getSsdCapacity();
    public abstract float getScreenSize();
    public abstract int getMemorySize();
    public abstract void Update(String category, String type, String brand, String cpu, float price, int memorySize, int ssdCapacity, float screenSize);
}
