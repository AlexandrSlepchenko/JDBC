package hibernate.Lesson3.DAO;

import hibernate.Lesson3.utils.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GeneralDAO<T> {

    private Class<T> clazz;

    public void setClass(Class<T> t) {
        this.clazz = t;
    }

    protected T save(T t) {
        try (Session session = Util.createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();
            session.save(t);
            tr.commit();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return t;
    }

    protected T update(T t) {
        try (Session session = Util.createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();
            session.update(t);
            tr.commit();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return t;
    }

    protected void delete(long id) {

        try (Session session = Util.createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();
            session.delete(session.get(clazz, id));
            tr.commit();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
    }

    protected T findById(long id) {
        try (Session session = Util.createSessionFactory().openSession()) {

            return session.get(clazz, id);

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
