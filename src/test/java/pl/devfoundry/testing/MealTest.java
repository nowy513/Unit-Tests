package pl.devfoundry.testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.devfoundry.testing.extension.IAExceptionIgnoreExtension;
import pl.devfoundry.testing.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;


class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
//        Given
        Meal meal = new Meal(35);

//        When
        int discountedPrice = meal.getDiscountedPrice(7);

//        Then
        assertEquals(28, discountedPrice);
        assertThat(discountedPrice, is(28));
//        assertThat(discountedPrice).isEqualTo(28);
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual(){
//        Given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

//        Then
        assertSame(meal1, meal2);
        assertThat(meal1, is(meal2));
//        assertThat(meal1).isSameAs(meal2);
    }

    @Test
    void referencesToDifferentObjectShouldBeNotEqual(){
//        Given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

//        Then
        assertNotSame(meal1, meal2);
//        assertThat(meal1).isNotSameAs(meal2);
        assertThat(meal1, not(is(meal2)));
    }

    @Test
    void twoMealsShouldBeEqualsWhenPriceAndNameAreTheSame(){
//        Given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10,"Pizza" );

//        Then
        assertEquals(meal1, meal2);
//        assertThat(meal1).isEqualTo(meal2);
        assertThat(meal1, equalTo(meal2));
    }

    @Test
    void exceptionShouldBeThrowIfDiscountIsHigherThanPrice(){

//        Given
        Meal meal = new Meal(8, "Soup");

//        When
//        Then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(40));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 18})
    void mealPricesShouldBeLowerThan20(int price){
            assertThat(price, lessThan(20));

    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgerShouldHaveCorrectNameAndPrice(String name, int price){
        assertThat(name, containsString("burger"));
        assertThat(price,greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name){
        assertThat(name, notNullValue());
        assertThat(name, notNullValue());
    }

    private static Stream<String> createCakeNames(){
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitecake", "Cupcake");
        return cakeNames.stream();
    }

    @ExtendWith(IAExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 8})
    void mealPricesShouldBeLowerThan10(int price){

        if (price > 5){
            throw new IllegalArgumentException();
        }
        assertThat(price, lessThan(20));

    }

    @Tag("fries")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices(){
        Order order = new Order();
        order.addMealToOrder(new Meal(10, 2, "Hamburger"));
        order.addMealToOrder(new Meal(7, 4, "Fries"));
        order.addMealToOrder(new Meal(2, 3, "Pizza"));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i= 0; i < order.getMeals().size(); i++){

            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(67));
            };

            String name = "Test name: " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }

    @Test
    void testMealSumPrice() {

//            Given
        Meal meal = mock(Meal.class);

        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);
        given(meal.sumPrice()).willCallRealMethod();

//        When
        int result = meal.sumPrice();

//        Then
        assertThat(result, equalTo(45));

    }

    private int calculatePrice(int price, int quantity){
        return price * quantity;
    }
}