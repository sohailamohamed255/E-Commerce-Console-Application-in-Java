import java.time.LocalDate;

public class NonShippableProduct extends Product {
    public NonShippableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity, expirationDate);
    }
}
