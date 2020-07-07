package hibernate.Lesson3.DAO;

import hibernate.Lesson3.Model.Filter;
import hibernate.Lesson3.Model.Room;
import hibernate.Lesson3.utils.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAO extends GeneralDAO<Room> {
    public RoomDAO() {
        setClass(Room.class);
    }

    public List findHotelByName(Filter filter) {

        try (Session session = Util.createSessionFactory().openSession()) {

            Query query = session.createQuery("FROM Room WHERE Room.numberOfGuests = :numberOfGuests " +
                    "AND Room.price BETWEEN :minPrice AND :maxPrice " +
                    "AND Room.breakfastIncluded = :breakfastIncluded " +
                    "AND Room.petsAllowed =:petsAllowed " +
                    "AND Room.dateAvailableFrom <:dateAvailableFrom " +
                    "AND Room.hotel.name =:hotelName " +
                    "AND Room.hotel.country =:country " +
                    "AND Room.hotel.city =:city");

            setParam(query, filter);

            return query.list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Room saveRoom(Room room) {
        return save(room);
    }

    public Room updateRoom(Room room) {
        return update(room);
    }

    public void deleteRoom(long id) {
        delete(id);
    }

    public Room getRoomById(long id) {
        return findById(id);
    }

    private void setParam(Query query, Filter filter) {

        if (filter.getNumberOfGuests() == 0) {
            query.setParameter("numberOfGuests", 0);
        } else {
            query.setParameter("numberOfGuests", filter.getNumberOfGuests());
        }

        if (filter.getPrice() == 0 && filter.getDeltaPrice() == 0) {
            query.setParameter("minPrice", 0);
            query.setParameter("maxPrice", 0);
        } else {
            query.setParameter("minPrice", filter.getPrice() - filter.getDeltaPrice());
            query.setParameter("maxPrice", filter.getPrice() + filter.getDeltaPrice());
        }

        if (!filter.isBreakfastIncluded()) {
            query.setParameter("breakfastIncluded", 0);
        } else {
            query.setParameter("breakfastIncluded", filter.isBreakfastIncluded());
        }

        if (!filter.isPetsAllowed()) {
            query.setParameter("petsAllowed", 0);
        } else {
            query.setParameter("petsAllowed", filter.isPetsAllowed());
        }

        if (filter.getDateAvailableFrom() == null) {
            query.setParameter("dateAvailableFrom", 0);
        } else {
            query.setParameter("dateAvailableFrom", filter.getDateAvailableFrom());
        }

        if (filter.getHotelName() == null) {
            query.setParameter("hotelName", 0);
        } else {
            query.setParameter("hotelName", filter.getHotelName());
        }

        if (filter.getCountry() == null) {
            query.setParameter("country", 0);
        } else {
            query.setParameter("country", filter.getCountry());
        }

        if (filter.getCity() == null) {
            query.setParameter("city", 0);
        } else {
            query.setParameter("city", filter.getCity());
        }

    }
}
