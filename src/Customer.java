public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Cart getCart() {
        return cart;
    }

    public void checkout() throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty.");
        }

        double subtotal = cart.getSubtotal();
        double shippingFees = 0;

        for (Shippable item : cart.getShippableItems()) {
            shippingFees += item.getWeight() * 2.0;
        }

        double total = subtotal + shippingFees;

        if (total > balance) {
            throw new Exception("Insufficient balance. Total = $" + total + ", Balance = $" + balance);
        }

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        balance -= total;

        System.out.println("\n=== Checkout Summary ===");
        System.out.println("Customer: " + name);
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Shipping Fees: $" + shippingFees);
        System.out.println("Total Paid: $" + total);
        System.out.println("Remaining Balance: $" + balance);

        if (!cart.getShippableItems().isEmpty()) {
            ShippingService.send(cart.getShippableItems());
        }

        cart.clear();
    }
}
