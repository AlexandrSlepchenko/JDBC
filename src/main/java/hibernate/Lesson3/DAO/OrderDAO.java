package hibernate.Lesson3.DAO;

import hibernate.Lesson3.Model.Order;

public class OrderDAO extends GeneralDAO<Order> {
    public OrderDAO() {
        setClass(Order.class);
    }

    public Order saveOrder(Order order) {
        return save(order);
    }

    public Order updateOrder(Order order) {
        return update(order);
    }

    public void deleteOrder(long id) {
        delete(id);
    }

    public Order getOrderById(long id) {
        return findById(id);
    }
}
