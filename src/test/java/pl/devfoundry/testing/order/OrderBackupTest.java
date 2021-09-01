package pl.devfoundry.testing.order;

import org.junit.jupiter.api.*;
import pl.devfoundry.testing.meal.Meal;

import java.io.FileNotFoundException;
import java.io.IOException;

public class OrderBackupTest {

    private static OrderBackup orderBackup;

    @BeforeAll
    static void setup() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeFile();
    }

    @BeforeEach
    void appendAtTheStartOfTheLine() throws IOException {
        orderBackup.getWriter().append("New order: ");
    }

    @AfterEach
    void appendAtTheEndOfTheLine() throws IOException {
        orderBackup.getWriter().append(" back up");
    }

    @Tag("fries")
    @Test
    void backupOrderWithOneMeal() throws IOException {

//        Given
        Meal meal = new Meal(7, "Fries");
        Order order = new Order();
        order.addMealToOrder(meal);

//        When
        orderBackup.backupOrder(order);

//        Then
        System.out.println("Order: " + order.toString() + " backed up.");
    }
}
