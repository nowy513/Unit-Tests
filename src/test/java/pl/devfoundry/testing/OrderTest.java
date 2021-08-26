package pl.devfoundry.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(BeforeAfterExtension.class)
public class OrderTest {

    private Order order;
    @BeforeEach
    void initializeOrder(){
        System.out.println("Before each");
        order = new Order();
    }

    @AfterEach
    void cleanUp(){
        System.out.println("After each");
        order.cancel();
    }

    @Test
    void testAssertArrayEquals(){

//        Given
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

//        Then
        assertArrayEquals(ints1, ints2);

    }

    @Test
    void mealListShouldBeEmptyAfterCreationOrder(){

//        Then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){

//        Given
        Meal meal = new Meal(15, "Burger");

//        When
        order.addMealToOrder(meal);

//        Then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals().get(0).getPrice(), equalTo(15));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){
        //        Given
        Meal meal = new Meal(15, "Burger");

//        When
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

//        Then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));

    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingItemToOrder(){
        //        Given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwitch");

//        When
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

//        Then
        assertThat(order.getMeals().get(0), is(meal1));
        assertThat(order.getMeals().get(1), is(meal2));

    }

    @Test
    void testIfTwoMealListsAreTheSame(){

        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwitch");
        Meal meal3 = new Meal(11, "Kebab");

        List<Meal> meals1 = Arrays.asList(meal1, meal2);
        List<Meal> meals2 = Arrays.asList(meal1, meal2);

//        Then
        assertThat(meals1, is(meals2));
    }

    @Test
    void orderTotalPriceShouldNotExceedMaxInValue(){

        //        Given
        Meal meal1 = new Meal(Integer.MAX_VALUE, "Burger");
        Meal meal2 = new Meal(Integer.MAX_VALUE, "Sandwitch");

//        When
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

//        Then
      assertThrows(IllegalStateException.class, () -> order.totalPrice());
    }

    @Test
    void emptyOrderTotalPriceShouldEqualZero(){

//        Given
//        Order is created in BeforeEach

//        Then
        assertThat(order.totalPrice(), is(0));
    }

    @Test
    void cancelingOrderShouldRemoveAllItemsFromMealsList(){

        //        Given
        Meal meal1 = new Meal(Integer.MAX_VALUE, "Burger");
        Meal meal2 = new Meal(Integer.MAX_VALUE, "Sandwitch");

//        When
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        order.cancel();

//        Then
        assertThat(order.getMeals().size(), is(0));
    }
}
