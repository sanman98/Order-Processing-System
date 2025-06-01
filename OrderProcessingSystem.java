package projectds1;
import java.util.Scanner;

public class OrderProcessingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PriorityOrderQueue orderQueue = PriorityOrderQueue.loadFromFile("orders.dat");
        CompletedOrders completedOrders = CompletedOrders.loadFromFile("completed.dat");
        CancelledOrders cancelledOrders = CancelledOrders.loadFromFile("cancelled.dat");
        Inventory inventory = new Inventory();

        while (true) {
            System.out.println("\n--- Order Processing System ---");
            System.out.println("1. Add Order");
            System.out.println("2. Fulfill Order");
            System.out.println("3. Cancel Order");
            System.out.println("4. View Pending Orders");
            System.out.println("5. View Completed Orders");
            System.out.println("6. View Cancelled Orders");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter Order Details: ");
                    String orderDetails = scanner.nextLine();
                    System.out.print("Priority (1 for High, 0 for Normal): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();

                    if (inventory.checkStock(orderDetails)) {
                        Order order = new Order(orderId, customerName, orderDetails, priority);
                        orderQueue.addOrder(order);
                        System.out.println("Order added successfully!");
                    } else {
                        System.out.println("Items are not in stock. Cannot add order.");
                    }
                    break;
                case 2:
                    Order fulfilled = orderQueue.fulfillOrder();
                    if (fulfilled != null) {
                        completedOrders.addCompletedOrder(fulfilled);
                        System.out.println("Order fulfilled: " + fulfilled.orderId);
                    } else {
                        System.out.println("No pending orders to fulfill.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Order ID to cancel: ");
                    int cancelId = scanner.nextInt();
                    scanner.nextLine();
                    Order cancelled = orderQueue.cancelOrder(cancelId);
                    if (cancelled != null) {
                        cancelledOrders.addCancelledOrder(cancelled);
                        System.out.println("Order cancelled: " + cancelled.orderId);
                    } else {
                        System.out.println("Order ID not found.");
                    }
                    break;
                case 4:
                    orderQueue.displayOrders();
                    break;
                case 5:
                    completedOrders.displayCompletedOrders();
                    break;
                case 6:
                    cancelledOrders.displayCancelledOrders();
                    break;
                case 7:
                    orderQueue.saveToFile("orders.dat");
                    completedOrders.saveToFile("completed.dat");
                    cancelledOrders.saveToFile("cancelled.dat");
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
