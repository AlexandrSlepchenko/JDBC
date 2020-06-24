package Lesson5;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private SessionFactory sessionFactory;

    public SessionFactory createSession(){
        return new Configuration().configure().buildSessionFactory();
    }

    public void shutDown(){
        sessionFactory.close();
    }

}
