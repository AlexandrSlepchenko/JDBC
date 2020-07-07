package hibernate.Lesson3.DAO;

import hibernate.Lesson2.HW.HQL.Product;
import hibernate.Lesson3.Model.Hotel;
import hibernate.Lesson3.utils.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {
    public HotelDAO() {
        setClass(Hotel.class);
    }

    public List<Hotel> findHotelByName(String name) {

        try (Session session = Util.createSessionFactory().openSession()) {

            Query query = session.createQuery("FROM Hotel WHERE Hotel.name = :name");

            query.setParameter("name", name);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Hotel> findHotelByCity(String city) {

        try (Session session = Util.createSessionFactory().openSession()) {

            Query query = session.createQuery("FROM Hotel WHERE Hotel.city = :city");

            query.setParameter("city", city);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Hotel saveHotel(Hotel hotel) {
        return save(hotel);
    }

    public Hotel updateHotel(Hotel hotel) {
        return update(hotel);
    }

    public void deleteHotel(long id) {
        delete(id);
    }

    public Hotel getHotelById(long id) {
        return findById(id);
    }
}
