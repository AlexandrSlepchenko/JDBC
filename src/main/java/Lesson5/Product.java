package Lesson5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product {
    private long id;
    private String name;
    private String Description;
    private int price;

    public Product() {
    }

    @Id
    @Column(name ="ID")
    public long getId() {
        return id;
    }

    @Column(name ="NAME")
    public String getName() {
        return name;
    }

    @Column(name ="DESCRIPTION")
    public String getDescription() {
        return Description;
    }

    @Column(name ="PRICE")
    public int getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
