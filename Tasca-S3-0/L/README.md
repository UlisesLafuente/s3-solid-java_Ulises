# L - Liskov Substitution Principle (LSP)

## What is it?

The **Liskov Substitution Principle (LSP)** states that:

> **Subclasses must be able to substitute for their superclasses without altering the program's behavior.**

This means that any derived class should **behave like its parent class**. If a subclass **breaks contracts or behaviors** defined in the base class, then it violates this principle.

In other words, if a program is designed to work with an object of a base class, it should work correctly if that object is substituted with any object of a derived class.

## Why is it important?

When we use **inheritance**, we expect a subclass to **extend the behavior of the superclass, not break it**. If LSP is not respected, we can have difficult-to-detect errors and systems that are difficult to maintain or extend.

### **Example:**

Suppose you are designing an application that works with various **cryptocurrencies** and offers an API like this:
```
java
public class Wallet {
    private String nameCrypto;
    private String cancelCode;

    public Wallet(String nameCrypto, String cancelCode) {
        this.nameCrypto = nameCrypto;
        this.cancelCode = cancelCode;
    }

    public void sendMoney(String destination, double amount) {
        System.out.println("Money is being sent via the blockchain of " + nameCrypto);
    }

    public void cancelTransaction(String transactionId) {
        if (AuthorizationCancel.cancel(cancelCode, transactionId))
            System.out.println("Transaction " + transactionId + " is being cancelled with the code" + cancelCode);
        else throw new TransactionCancelException("Transaction could not be cancelled");
    }
}
```
**Your API works with:**
- `Tezos`
- `Ethereum`
- `Monero`
```java
public class TezosWallet extends Wallet{
    public TezosWallet(){
        super("Tezos", "TEZ_0974_BLCH");
    }
}

public class EthereumWallet extends Wallet{
    public EthereumWallet(){
        super("Ethereum", "ETH_7637_BLCH");
    }
}

public class MoneroWallet extends Wallet{
    public MoneroWallet(){
        super("Monero", null); //Monero does not allow cancelling transactions
    }
}
```
Problem: `MoneroWallet` inherits from `Wallet`, but whenever someone calls `cancelTransaction()` with a `MoneroWallet` instance, the **program will always break** because Monero does not allow cancellations.
Therefore, a `MoneroWallet` cannot be used in contexts that expect `Wallet.cancelTransaction()` to work correctly – this **violates LSP**.
Solution: Apply LSP with a **clear hierarchy**:
> The key is **separating responsibilities**: not all cryptocurrencies allow cancellations, so not all wallets should have this method.
- **1 Create an interface for cancellation:**
```java
public interface CancellationStrategy {
    void cancel(String id);
}
```
- **2 Implement the real and null strategy:**
```java
public class AllowedCancellationStrategy implements CancellationStrategy {
    private String cancelCode;

    public AllowedCancellationStrategy(String cancelCode) {
        this.cancelCode = cancelCode;
    }

    @Override
    public void cancel(String id) {
        System.out.println("Transaction " + id + " is being cancelled with the code " + cancelCode);
    }
}

public class DisallowedCancellationStrategy implements CancellationStrategy {
    @Override
    public void cancel(String id) {
        // Does nothing, simply ignores the cancellation.
        System.out.println("Cancellation is not supported by this wallet, the transaction is ignored: " + id);
    }
}
```
- **3 Modify the Wallet class to use the cancellation strategy:**
```java
public class Wallet {
    private String nameCrypto;
    private CancellationStrategy cancellationStrategy;

    public Wallet(String nameCrypto, CancellationStrategy cancellationStrategy) {
        this.nameCrypto = nameCrypto;
        this.cancellationStrategy = cancellationStrategy;
    }

    public void sendMoney(String destination, double amount) {
        System.out.println("Money is being sent via the blockchain of " + nameCrypto);
    }

    public void cancelTransaction(String id) {
        cancellationStrategy.cancel(id);
    }
}
```
- **4 Create wallets with the appropriate strategy:**
```java
public class TezosWallet extends Wallet {
    public TezosWallet() {
        super("Tezos", new AllowedCancellationStrategy("TEZ_0974_BLCH"));
    }
}

public class EthereumWallet extends Wallet {
    public EthereumWallet() {
        super("Ethereum", new AllowedCancellationStrategy("ETH_7637_BLCH"));
    }
}

public class MoneroWallet extends Wallet {
    public MoneroWallet() {
        super("Monero", new DisallowedCancellationStrategy());
    }
}
```

---
## Objective of the exercise
Find a Java class that **misuses inheritance** and, as a consequence, **violates the Liskov Substitution Principle**.
Your challenge is:
1. Identify the hierarchy that **breaks the expected behavior**.
2. Refactor the code to ensure that **subclasses can substitute for their superclasses without breaking** the logic.
3. Apply **abstraction and polymorphism** to make the code more flexible and robust.
---
## Tips for Applying LSP
**Ensure that all subclasses comply with the contract of the superclass.**
**Do not use inheritance only to reuse code.**
**Consider patterns like Composition* over Inheritance when there is not a clear type relationship.**
\*“Composition” is a concept of **OOP** that means building a class using other objects (other classes) as internal parts, instead of creating a hierarchy of inheritance (subclasses).
---
## Reflection
When you correctly apply **the Liskov Substitution Principle**:
- You avoid unexpected behavior in execution.
- Your code is more predictable, secure, and reusable.
- You can use polymorphism without surprises.
  **Inheriting behavior means respecting it, not breaking it.**