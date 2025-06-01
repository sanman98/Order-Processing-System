package projectds1;
public class OrderQueue {
    protected Order front;
    protected Order rear;

    public void addOrder(Order order) {
        if (front == null) {
            front = rear = order;
        } else {
            rear.next = order;
            rear = order;
        }
    }

    public Order fulfillOrder() {
        if (front == null) return null;
        Order fulfilled = front;
        front = front.next;
        if (front == null) rear = null;
        fulfilled.next = null;
        fulfilled.status = "Completed";
        return fulfilled;
    }

    public Order cancelOrder(int orderId) {
        Order current = front;
        Order previous = null;
        while (current != null) {
            if (current.orderId == orderId) {
                if (previous == null) {
                    front = current.next;
                } else {
                    previous.next = current.next;
                }
                if (current == rear) rear = previous;
                current.next = null;
                current.status = "Cancelled";
                return current;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public void displayOrders() {
        if (front == null) {
            System.out.println("No pending orders.");
            return;
        }
        Order temp = front;
        while (temp != null) {
            System.out.println("Order ID: " + temp.orderId + ", Customer: " + temp.customerName + ", Priority: " + (temp.priority == 1 ? "High" : "Normal") + ", Status: " + temp.status);
            temp = temp.next;
        }
    }
}