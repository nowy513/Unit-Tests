package pl.devfoundry.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
        assertThat(name, endsWith("cake"));
    }

    private static Stream<String> createCakeNames(){
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitecake", "Cupcake");
        return cakeNames.stream();
    }

}