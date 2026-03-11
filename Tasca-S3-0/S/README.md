#  S - Single Responsibility Principle (SRP)

##  What is it?

The **Single Responsibility Principle** states that:

> **A class should have one reason to change.**

In other words, a class should have **one sole responsibility**, or **one single motive for being modified**.

 **Example:**
If you have a `Report` class that:
- generates content,
- prints the report,
- and saves the report.
```
java
public class Report {
    private String content;

    public Report(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }

    public void print() {
        System.out.println("Printing report:");
        System.out.println(content);
    }

    public void save(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            System.out.println("Report saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving report: " + e.getMessage());
        }
    }
}
```
 **Problem:** Each of these functions **has a different responsibility**, and they should be separated into different classes.
 **You are violating this principle!**
 **Refactored version applying SRP:** We separate the responsibilities into different classes:
- ** Report: Only contains the content.**
- 
```java
// Class with a single responsibility: maintain the content
public class Report {
    private String content;

    public Report(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
```
- ** Printer: Responsible for printing reports.**
```java
// Class with a single responsibility: print reports
public class Printer {
    public void printReport(Report report) {
        System.out.println("Printing report:");
        System.out.println(report.getContent());
    }
}
```
- ** Saver: Responsible for saving the report.**
```java
// Class with a single responsibility: save reports
public class Saver {
    public void saveReport(Report report, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(report.getContent());
            System.out.println("Report saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving report: " + e.getMessage());
        }
    }
}
```
- ** Example Usage:**
```java
public class Main {
    public static void main(String[] args) {
        Report report = new Report("This is the report content.");

        Printer printer = new Printer();
        printer.printReport(report);

        Saver saver = new Saver();
        saver.saveReport(report, "report.txt");
    }
}
```

---
##  Exercise Objective
In the attached Java file, you will find a class that **does not respect this principle**: it does too many things at once.
 **Your challenge is:**
1.  Analyze the multiple responsibilities that the class has.
2.  Separate them into **different classes**, each with a clear single responsibility.
3.  Maintain the code readable, modular, and easy to maintain.
---
##  Tips for Applying SRP
 Ask yourself: *"What reasons would this class have to change?" and "What are the responsibilities of this class?"*
 If there are more than one... it’s time to separate responsibilities!
 Don’t be afraid to create **smaller, more focused classes**.
---
##  Reflection
When a class has only one responsibility:
- It's easier to read.
- It's easier to test.
- It's less likely to generate errors when you change a functionality.
   **Less coupling, more cohesion.**
---
 Let's go! Review the code, apply the SRP principle, and enjoy the refactoring process.
 **How many responsibilities does the class have?**