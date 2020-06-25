package hibernate.Lesson2.HW.HQL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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

            Query query = session.createQuery("FROM hibernate.Lesson2.HW.HQL.Product Product WHERE Product.name = :name");

            query.setParameter("name", name);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Product> findByContainedName(String name) {

        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery("FROM hibernate.Lesson2.HW.HQL.Product Product WHERE Product.name LIKE :name");

            query.setParameter("name", name);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByPrice(int price, int delta) {

        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery("FROM hibernate.Lesson2.HW.HQL.Product Product WHERE Product.price BETWEEN :minPrice AND :maxPrice");

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
            Query query = session.createQuery("FROM hibernate.Lesson2.HW.HQL.Product Product WHERE Product.name = :name ORDER BY Product.name ASC");

            query.setParameter("name", name);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByNameSortedDesc(String name) {
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("FROM hibernate.Lesson2.HW.HQL.Product Product WHERE Product.name = :name ORDER BY Product.name DESC");

            query.setParameter("name", name);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByPriceSortedDesc(int price, int delta) {
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("FROM hibernate.Lesson2.HW.HQL.Product Product WHERE Product.price BETWEEN :minPrice AND :maxPrice ORDER BY Product.price DESC");

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
