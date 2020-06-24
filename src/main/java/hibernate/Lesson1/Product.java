package hibernate.Lesson1;

import javax.persistence.*;

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
    @SequenceGenerator(name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
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
