#  O - Open/Closed Principle (OCP)

##  What is it?

The **Open/Closed Principle** states that:

> **Code should be open for extension, but closed for modification**

This means we should be able to **add new functionalities** to our system **without having to modify existing code**.

###  **Example:**

Suppose you have a class `DiscountCalculator` with this method:
```
java
public double calculateDiscount(Product product) {
if (product.getType().equals("Christmas")) {
return product.getPrice() * 0.2;
} else if (product.getType().equals("BlackFriday")) {
return product.getPrice() * 0.3;
}
return 0;
}
```
 **Problem:** Every time you want to add a new discount type, **you have to modify this class**.
 This **violates the OCP**, because the code is closed to modification.
 **The solution is to use polymorphism or design patterns (such as Strategy or Factory), which allow you to add new behaviors without touching existing code:**
- **1 Define a common interface:**
```java
public interface DiscountStrategy {
    double applyDiscount(Product product);
}
```
- **2 Implement concrete strategies:**
```java
public class ChristmasDiscount implements DiscountStrategy {
    public double applyDiscount(Product product) {
        return product.getPrice() * 0.2;
    }
}

public class BlackFridayDiscount implements DiscountStrategy {
    public double applyDiscount(Product product) {
        return product.getPrice() * 0.3;
    }
}

public class BirthdayDiscount implements DiscountStrategy {
    public double applyDiscount(Product product) {
        return product.getPrice() * 0.1;
    }
}
```
- **3 Flexible and open for extension DiscountCalculator class:**
```java
public class DiscountCalculator {

    public double calculateDiscount(Product product, DiscountStrategy strategy) {
        return strategy.applyDiscount(product);
    }
}
```
- **4 Example of Usage:**
```java
public class Main {
    public static void main(String[] args) {
        Product product = new Product("Laptop", 1000);

        DiscountCalculator calculator = new DiscountCalculator();

        double discount = calculator.calculateDiscount(product, new ChristmasDiscount());
        System.out.println("Discount applied: " + discount);
    }
}
```

---
##  Exercise Objective
In the attached Java file, you will find a class that **does not respect the OCP**: it needs to be modified every time there is a change or extension of functionality.
 Your challenge is:
1. Identify which part of the code is **too exposed to modifications**.
2. Refactor it to make it **easily extensible** without altering existing behavior.
3. Apply **abstractions and polymorphism** to make the code more flexible and robust.
---
##  Tips for Applying OCP
 **Avoid conditional statements (if/else, switch) to determine behaviors that may vary over time.**
 **Define interfaces or abstract classes** that allow you to add new functionalities without touching existing code.
 **Use patterns such as Strategy, Factory, or Chain of Responsibility** depending on the context.
---
##  Reflection

When a
system is well-designed according to **OCP**:
- You can
  **add new functionalities** with ease.
- Your code is **more stable** and less vulnerable to regressions.
- You improve
  **reusability and maintainability**.
   **Extensible, but secure**. This is the power of OCP.
  `*` **Regression** means that a functionality that **previously worked correctly, now no longer works** after making changes to the code.
---
 Let's go! Review the code, identify how it can be improved, and apply the OCP principle to make it more modular and ready for the future.
 **Are you able to add a new product without changing the code?**
```