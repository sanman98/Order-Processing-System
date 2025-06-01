package projectds1;
import java.io.*;

public class PriorityOrderQueue extends OrderQueue implements Serializable {
    private static final long serialVersionUID = 1L;
    private Order priorityFront;
    private Order priorityRear;

    public void addOrder(Order order) {
        if (order.priority == 1) {
            if (priorityFront == null) {
                priorityFront = priorityRear = order;
            } else {
                priorityRear.next = order;
                priorityRear = order;
            }
        } else {
            super.addOrder(order);
        }
    }

    public Order fulfillOrder() {
        if (priorityFront != null) {
            Order fulfilled = priorityFront;
            priorityFront = priorityFront.next;
            if (priorityFront == null) priorityRear = null;
            fulfilled.next = null;
            fulfilled.status = "Completed";
            return fulfilled;
        } else {
            return super.fulfillOrder();
        }
    }

    public Order cancelOrder(int orderId) {
        Order current = priorityFront;
        Order previous = null;
        while (current != null) {
            if (current.orderId == orderId) {
                if (previous == null) {
                    priorityFront = current.next;
                } else {
                    previous.next = current.next;
                }
                if (current == priorityRear) priorityRear = previous;
                current.next = null;
                current.status = "Cancelled";
                return current;
            }
            previous = current;
            current = current.next;
        }
        return super.cancelOrder(orderId);
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PriorityOrderQueue loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (PriorityOrderQueue) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new PriorityOrderQueue();
        }
    }
}
