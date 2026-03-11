#  I - Principles of Interface Segregation (ISP)

##  What is it?

The **Principle of Interface Segregation (ISP)** states that:

> **A class should not be forced to implement methods it doesn't need.**

This means that interfaces **should be specific and well-defined based on functionality**. If an interface is too large or generic, it can force classes to implement methods that don't make sense for them.

 This problem is known as **fat interfaces** (fat interfaces) or **"code smell"** of **inflated interfaces** or with too many responsibilities.

`*` **"Code smell"** refers to a characteristic of source code that suggests a deeper problem or a **potential error in design or code structure**.

##  Why is it important?

Having **smaller, more specific interfaces** makes it easier to **reuse** the same interfaces in different parts of the system **without causing compatibility issues**.

###  **Example:**

Let's say you're designing an application that works with **various types of printers** and you offer an interface like this:
```
java
public interface Printer {
    void print(String document);
    void scan(String document);
    void sendFax(String document);
}

public class BasicPrinter implements Printer {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void scan(String document) {
        throw new UnsupportedOperationException("This printer does not scan.");
    }

    @Override
    public void sendFax(String document) {
        throw new UnsupportedOperationException("This printer does not send faxes.");
    }
}
```
 **The Problem** with this interface is that it groups **too many responsibilities** into a single interface. Not all printers have the capacity to scan or send faxes, but they are still **forced to implement** these methods.
 This violates the **Principle of Interface Segregation (ISP)**
 **Solution with ISP:**
> Divide the Printer interface into **smaller, more specific interfaces** (Printer, Scanner, Fax), and make each class implement **only the ones it needs**.
- **1 Segregated Interfaces based on Functionality:**
```java
public interface Printer {
    void print(String document);
}

public interface Scanner {
    void scan(String document);
}

public interface Fax {
    void sendFax(String document);
}
```
- **2 Basic Printer: Only Prints:**
```java
public class BasicPrinter implements Printer {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }
}
```
- **3 Printer/Scanner: Prints and Scans:**
```java
public class PrinterScanner implements Printer, Scanner {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println("Scanning: " + document);
    }
}
```
- **4 Multifunction Printer: Prints, Scans and Sends Faxes:**
```java
public class MultifunctionPrinter implements Printer, Scanner, Fax {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println("Scanning: " + document);
    }

    @Override
    public void sendFax(String document) {
        System.out.println("Sending fax: " + document);
    }
}
```

---
##  Objective of the Exercise
In the attached Java file, you will find a class or class hierarchy that implements a **too-large interface**.
 Your challenge is:
1.  Detect which methods **make no sense** for some of the classes.
2.  Refactor the interface into **smaller, more focused interfaces**.
3.  Make each class implement **only the interfaces it needs**.
---
##  Tips for Applying ISP
 **If a class needs to implement a method that only throws an exception or is empty... you may be violating ISP.**
 **Prefer multiple smaller, specific interfaces over one large, generic interface.**
 **Smaller, focused interfaces promote more flexibility and maintainability.**
---
##  Reflection
When following **ISP**:
- Classes are simpler and more coherent.
- We avoid implementing absurd or unnecessary methods.
- It becomes easier to use composition instead of forced inheritance.
 **More modularity, less coupling.**
---
 Let's go! Review the interface, apply the **ISP** principle, and refactor with elegance.
 **Does your interface do too many things? What parts could be divided?**
```
