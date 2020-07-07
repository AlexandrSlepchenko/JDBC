package hibernate.Lesson3.DAO;

import hibernate.Lesson3.Model.Hotel;
import hibernate.Lesson3.Model.Order;
import hibernate.Lesson3.Model.User;
import hibernate.Lesson3.utils.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO extends GeneralDAO<User> {
    public UserDAO() {
        setClass(User.class);
    }

    public List<Order> findOrdersByUserId(Long id) {

        try (Session session = Util.createSessionFactory().openSession()) {

            Query query = session.createQuery("FROM Order WHERE Order.userOrdered = :id");

            query.setParameter("id", id);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public User saveUser(User user) {
        return save(user);
    }

    public User updateUser(User user) {
        return update(user);
    }

    public void deleteUser(long id) {
        delete(id);
    }

    public User getUserById(long id) {
        return findById(id);
    }
}
