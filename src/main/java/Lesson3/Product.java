package Lesson3;

public class Product {
    private long id;
    private String name;
    private String Description;
    private int price;

    public Product(long id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        Description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "src.main.java.Lesson1andLesson2.Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Description='" + Description + '\'' +
                ", price=" + price +
                '}';
    }
}
