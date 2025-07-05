import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static void printLine() {
        System.out.println("--------------------------------------------------");
    }

    private static void pause(Scanner scanner) {
        System.out.print("\nPress [Enter] to continue...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        products.add(new ShippableProduct("Cheese", 5.0, 10, LocalDate.now().plusDays(3), 1.5));
        products.add(new ShippableProduct("TV", 300.0, 5, LocalDate.now().plusDays(365), 10.0));
        products.add(new NonShippableProduct("Mobile Scratch Card", 1.0, 50, null));
        products.add(new NonShippableProduct("Biscuits", 2.5, 20, LocalDate.now().minusDays(1)));

        System.out.println("🛍️  Welcome to EasyShop Console Store");
        printLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your balance: $");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        Customer customer = new Customer(name, balance);

        // Step 1: Shopping loop before cart menu
        boolean shopping = true;
        while (shopping) {
            printLine();
            System.out.println("🛒 AVAILABLE PRODUCTS");
            printLine();
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                String status = p.isExpired() ? "❌ Expired" : "✅ Available";
                String shipStatus = (p instanceof Shippable) ? "📦 Shippable" : "📭 No Shipping";

                System.out.printf("%d. %-25s $%-6.2f Qty: %-3d [%s | %s]\n",
                        (i + 1), p.getName(), p.getPrice(), p.getQuantity(), status, shipStatus);
            }
            System.out.print("\nEnter product number to add to cart (0 to finish): ");
            int productIndex = scanner.nextInt() - 1;
            scanner.nextLine();
            if (productIndex == -1) {
                shopping = false;
                break;
            }
            if (productIndex < 0 || productIndex >= products.size()) {
                System.out.println("❌ Invalid product number.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine();
            try {
                customer.getCart().addItem(products.get(productIndex), qty);
                System.out.println("✔ Product added to cart!");
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }

        // Step 2: Cart menu
        boolean inCartMenu = true;
        while (inCartMenu) {
            printLine();
            System.out.println("🧾 CART MENU");
            printLine();
            System.out.println("1. View Cart");
            System.out.println("2. Add Another Product");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. Proceed to Checkout");
            System.out.println("0. Exit");
            printLine();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customer.getCart().displayCart();
                    System.out.printf("🧾 Cart Subtotal: $%.2f\n", customer.getCart().getSubtotal());
                    pause(scanner);
                    break;
                case 2:
                    shopping = true;
                    break;
                case 3:
                    customer.getCart().displayCart();
                    if (customer.getCart().isEmpty()) {
                        pause(scanner);
                        break;
                    }
                    System.out.print("Enter cart item number to remove: ");
                    int removeIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    try {
                        customer.getCart().removeItem(removeIndex);
                        System.out.println("✔ Item removed.");
                    } catch (Exception e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    pause(scanner);
                    break;
                case 4:
                    try {
                        customer.checkout();
                        pause(scanner);
                        inCartMenu = false;
                    } catch (Exception e) {
                        System.out.println("❌ Checkout Failed: " + e.getMessage());
                        pause(scanner);
                    }
                    break;
                case 0:
                    System.out.println("👋 Thank you for shopping! Goodbye.");
                    inCartMenu = false;
                    break;
                default:
                    System.out.println("❌ Invalid option.");
                    pause(scanner);
            }

            // If user chose to continue shopping
            while (shopping) {
                printLine();
                System.out.println("🛒 AVAILABLE PRODUCTS");
                printLine();
                for (int i = 0; i < products.size(); i++) {
                    Product p = products.get(i);
                    String status = p.isExpired() ? "❌ Expired" : "✅ Available";
                    String shipStatus = (p instanceof Shippable) ? "📦 Shippable" : "📭 No Shipping";

                    System.out.printf("%d. %-25s $%-6.2f Qty: %-3d [%s | %s]\n",
                            (i + 1), p.getName(), p.getPrice(), p.getQuantity(), status, shipStatus);
                }
                System.out.print("\nEnter product number to add to cart (0 to finish): ");
                int productIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (productIndex == -1) {
                    shopping = false;
                    break;
                }
                if (productIndex < 0 || productIndex >= products.size()) {
                    System.out.println("❌ Invalid product number.");
                    continue;
                }

                System.out.print("Enter quantity: ");
                int qty = scanner.nextInt();
                scanner.nextLine();
                try {
                    customer.getCart().addItem(products.get(productIndex), qty);
                    System.out.println("✔ Product added to cart!");
                } catch (Exception e) {
                    System.out.println("❌ " + e.getMessage());
                }
            }
        }

        scanner.close();
    }
}
