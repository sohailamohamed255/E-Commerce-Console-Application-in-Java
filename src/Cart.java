import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) throws Exception {
        if (product.isExpired()) {
            throw new Exception("Cannot add expired product: " + product.getName());
        }

        if (quantity > product.getQuantity()) {
            throw new Exception("Not enough stock for product: " + product.getName());
        }

        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                int newQuantity = item.getQuantity() + quantity;

                if (newQuantity > product.getQuantity()) {
                    throw new Exception("Total quantity exceeds available stock for: " + product.getName());
                }

                // Replace with updated quantity
                items.set(items.indexOf(item), new CartItem(product, newQuantity));
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public void removeItem(int index) throws Exception {
        if (index < 0 || index >= items.size()) {
            throw new Exception("Invalid cart item number.");
        }
        items.remove(index);
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("🛒 Cart is empty.");
            return;
        }

        System.out.println("🛒 Your Cart:");
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            System.out.println((i + 1) + ". " + item.getProduct().getName() +
                    " x " + item.getQuantity() +
                    " ($" + item.getTotalPrice() + ")");
        }
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<Shippable> getShippableItems() {
        List<Shippable> shippables = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippables.add((Shippable) item.getProduct());
                }
            }
        }
        return shippables;
    }

    public void clear() {
        items.clear();
    }
}
