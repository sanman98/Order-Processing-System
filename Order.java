package projectds1;
import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    public int orderId;
    public String customerName;
    public String orderDetails;
    public String status; // "Pending", "Completed", "Cancelled"
    public int priority; // 1 = High Priority, 0 = Normal
    public transient Order next;

    public Order(int orderId, String customerName, String orderDetails, int priority) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDetails = orderDetails;
        this.status = "Pending";
        this.priority = priority;
        this.next = null;
    }
}