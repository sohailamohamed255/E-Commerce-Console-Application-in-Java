import java.util.List;

public class ShippingService {
    public static void send(List<Shippable> items) {
        System.out.println("\n📦 Shipping the following items:");
        
        double totalWeight = 0;

        for (Shippable item : items) {
            System.out.printf("- %s (%.2f kg)\n", item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }

        System.out.printf("🚚 Total Shipping Weight: %.2f kg\n", totalWeight);
    }
}
