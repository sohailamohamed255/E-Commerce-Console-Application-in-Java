# E-Commerce Console Application in Java

A console-based e-commerce simulation built with Java. This application demonstrates product handling, cart management, shipping logic, and customer checkout process.

## Features

* Define products with:

  1-Name, price, quantity
  2- Optional expiration date
  3- Optional shipping weight
* Add products to a shopping cart with a specified quantity
* Prevent adding expired or out-of-stock items
* Display cart summary and manage cart items (add/remove)
* Calculate:
  1- Subtotal
  2- Shipping fees (based on total weight)
  3- Final amount due
  4- Remaining customer balance
* Shipping service processes all shippable items

## Project Structure

```
src/
├── Product.java
├── ShippableProduct.java
├── NonShippableProduct.java
├── Shippable.java
├── Cart.java
├── CartItem.java
├── Customer.java
├── ShippingService.java
└── DeliverableExample.java
```

## Run it with me 

1. Make sure you have Java 11+ installed

2. Clone the repository:

```bash
git clone https://github.com/your-username/ecommerce-console-java.git
cd ecommerce-console-java/src
```

3. Compile the project:

```bash
javac -d out *.java
```

4. Run the main class:

```bash
java -cp out DeliverableExample
```

## The Output

```
📦 Shipping the following items:
- Cheese (0.20 kg)
- Cheese (0.20 kg)
- Biscuits (0.70 kg)
🚚 Total Shipping Weight: 1.10 kg

🧾 CHECKOUT RECEIPT
- 2x Cheese       $200.00
- 1x Biscuits     $150.00
- 1x Mobile Scratch Card  $50.00
-----------------------------
Subtotal:         $400.00
Shipping Fee:     $30.00
Total:            $430.00
Customer Balance: $570.00
```

## The Assumptions

* Shipping fee is calculated as `$30` if total weight > 0
* Expired items cannot be added to the cart
* Cart quantities must not exceed product stock
* Products with no expiration or shipping are valid

##  Contact
sohayla25520022@gmail.com
01144596055
