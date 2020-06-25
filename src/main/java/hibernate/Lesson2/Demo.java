package hibernate.Lesson2;

import hibernate.Lesson1.Product;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        List<Product> products = new ArrayList<>();

        Product product = new Product();
        product.setName("Big toy");
        product.setDescription("wery big toy");
        product.setPrice(1447);

        Product product2 = new Product();
        product2.setName("Big toy2");
        product2.setDescription("wery big toy");
        product2.setPrice(258);

        Product product3 = new Product();
        product3.setName("Big toy3");
        product3.setDescription("wery big toy");
        product3.setPrice(369);

        Product product4 = new Product();
        product4.setName("Big toy4");
        product4.setDescription("wery big toy");
        product4.setPrice(852);

        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        productDAO.saveAll(products);

    }
}
