package hibernate.Lesson3.Service;

import com.sun.org.apache.xpath.internal.operations.Or;
import hibernate.Lesson3.DAO.OrderDAO;
import hibernate.Lesson3.DAO.RoomDAO;
import hibernate.Lesson3.DAO.UserDAO;
import hibernate.Lesson3.Model.Order;
import hibernate.Lesson3.Model.Room;
import hibernate.Lesson3.Model.User;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();
    private UserDAO userDAO = new UserDAO();
    private OrderDAO orderDAO = new OrderDAO();

    @Transactional
    public void bookRoom(Long roomId, Long userId, Date dateFrom, Date dateTo) {

        Room room = roomDAO.getRoomById(roomId);
        User user = userDAO.getUserById(userId);

        if (room.getDateAvailableFrom().before(dateFrom) || room.getDateAvailableFrom().equals(dateFrom)) {

            room.setDateAvailableFrom(dateFrom);

            Order order = new Order();

            order.setUserOrdered(user);
            order.setDateFrom(dateFrom);
            order.setDateTo(dateTo);
            order.setRoom(room);
            order.setMoneyPaid(room.getPrice() * (dateTo.getTime() - dateFrom.getTime()));

            roomDAO.updateRoom(room);
            orderDAO.saveOrder(order);
        }
    }

    public void cancelReservation(Long roomId, Long userId) {
        Room room = roomDAO.getRoomById(roomId);
        User user = userDAO.getUserById(userId);
        List<Order> orders = userDAO.findOrdersByUserId(userId);

        for (Order order : orders) {
            if (order.getRoom().getId() == roomId) {
                room.setDateAvailableFrom(new Date());
                orderDAO.deleteOrder(order.getId());
            }
        }
    }

}
