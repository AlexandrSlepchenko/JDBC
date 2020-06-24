package Lesson5;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {
        Session session = new HibernateUtils().createSession().openSession();

        session.getTransaction().begin();

        Product product = new Product();

        product.setId(123);
        product.setName("Product");
        product.setDescription("Test Description");
        product.setPrice(1488);

        session.save(product);

        session.getTransaction().commit();

        System.out.println("Done");

        session.close();

    }
}
