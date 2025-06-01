package projectds1;
import java.io.*;

public class CancelledOrders implements Serializable {
    private static final long serialVersionUID = 1L;
    private Order head;

    public void addCancelledOrder(Order order) {
        order.next = head;
        head = order;
    }

    public void displayCancelledOrders() {
        if (head == null) {
            System.out.println("No cancelled orders.");
            return;
        }
        Order temp = head;
        while (temp != null) {
            System.out.println("[CANCELLED] Order ID: " + temp.orderId + ", Customer: " + temp.customerName);
            temp = temp.next;
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CancelledOrders loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (CancelledOrders) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new CancelledOrders();
        }
    }
}
