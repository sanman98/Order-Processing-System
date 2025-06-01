package projectds1;
import java.io.*;

public class CompletedOrders implements Serializable {
    private static final long serialVersionUID = 1L;
    private Order head;

    public void addCompletedOrder(Order order) {
        order.next = head;
        head = order;
    }

    public void displayCompletedOrders() {
        if (head == null) {
            System.out.println("No completed orders.");
            return;
        }
        Order temp = head;
        while (temp != null) {
            System.out.println("[COMPLETED] Order ID: " + temp.orderId + ", Customer: " + temp.customerName);
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

    public static CompletedOrders loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (CompletedOrders) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new CompletedOrders();
        }
    }
}