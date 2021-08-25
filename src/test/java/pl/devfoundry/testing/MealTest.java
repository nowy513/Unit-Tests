package pl.devfoundry.testing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(discountedPrice).isEqualTo(28);
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual(){
//        Given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

//        Then
        assertSame(meal1, meal2);
        assertThat(meal1).isSameAs(meal2);
    }

    @Test
    void referencesToDifferentObjectShouldBeNotEqual(){
//        Given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

//        Then
        assertNotSame(meal1, meal2);
        assertThat(meal1).isNotSameAs(meal2);
    }

    @Test
    void twoMealsShouldBeEqualsWhenPriceAndNameAreTheSame(){
//        Given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10,"Pizza" );

//        Then
        assertEquals(meal1, meal2);
        assertThat(meal1).isEqualTo(meal2);
    }

    @Test
    void exceptionShouldBeThrowIfDiscountIsHigherThanPrice(){

//        Given
        Meal meal = new Meal(8, "Soup");

//        When
//        Then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(40));
    }

}