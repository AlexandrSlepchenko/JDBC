package hibernate.Lesson2.HW.NativeSQL;

import hibernate.Lesson2.HW.HQL.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public Product findById(long id) {
        try {
            Session session = createSessionFactory().openSession();
            return session.get(Product.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Can`t find Product by Id " + id);
        }
        return null;
    }

    public List<Product> findByName(String name) {

        try (Session session = createSessionFactory().openSession()) {
//            List products = new ArrayList<>();
//
//            products = session.createNativeQuery("SELECT * FROM PRDODUCTS WHERE name = :productName")
//                    .setParameter("productName",name).list();
//
//            return products;

            NativeQuery query = session.createNativeQuery("SELECT * FROM PRODUCTS WHERE NAME = :name");
            query.addEntity(Product.class);
            query.setParameter("name", name);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Product> findByContainedName(String name) {

        try (Session session = createSessionFactory().openSession()) {

            NativeQuery query = session.createNativeQuery("SELECT * FROM PRODUCTS WHERE NAME LIKE :name");
            query.addEntity(Product.class);
            query.setParameter("name", "%" + name + "%");

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByPrice(int price, int delta) {

        try (Session session = createSessionFactory().openSession()) {

            NativeQuery query = session.createNativeQuery("SELECT * FROM PRODUCTS WHERE PRICE BETWEEN :minPrice AND :maxPrice");
            query.addEntity(Product.class);
            query.setParameter("minPrice", price-delta);
            query.setParameter("maxPrice", price+delta);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    public List<Product> findByNameSortedAsc(String name) {
        try (Session session = createSessionFactory().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT * FROM PRODUCTS WHERE NAME = :name ORDER BY NAME ASC ");
            query.addEntity(Product.class);
            query.setParameter("name", name);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByNameSortedDesc() {
        try (Session session = createSessionFactory().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT * FROM PRODUCTS ORDER BY NAME DESC");
            query.addEntity(Product.class);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByPriceSortedDesc(int price, int delta) {
        try (Session session = createSessionFactory().openSession()) {

            NativeQuery query = session.createNativeQuery("SELECT * FROM PRODUCTS WHERE PRICE BETWEEN :minPrice AND :maxPrice ORDER BY PRICE DESC");
            query.addEntity(Product.class);
            query.setParameter("minPrice", price-delta);
            query.setParameter("maxPrice", price+delta);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static SessionFactory sessionFactory;

    private SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    private void shutDown() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}