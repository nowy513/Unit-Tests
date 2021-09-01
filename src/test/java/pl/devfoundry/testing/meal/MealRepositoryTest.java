package pl.devfoundry.testing.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.devfoundry.testing.meal.Meal;
import pl.devfoundry.testing.meal.MealRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MealRepositoryTest {

    MealRepository mealRepository = new MealRepository();

    @BeforeEach
    void cleanUp(){
        mealRepository.getAllMeals().clear();
    }

    @Test
    void shouldBeAbleToAddMealToRepository(){

//        Given
        Meal meal = new Meal(10, "Pizza");

//        When
        mealRepository.add(meal);

//        Than
        assertThat(mealRepository.getAllMeals().get(0), is(meal));
    }

    @Test
    void shouldBeAbleToRemoveMealFromRepository(){

//        Given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

//        When
        mealRepository.delete(meal);

//        Then
        assertThat(mealRepository.getAllMeals(), not(contains(meal)));

    }

    @Test
    void shouldBeAbleToFindMealByExactName(){

//        Given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

//        When
        List<Meal> results = mealRepository.findByName("Pizza", true);

//        Then
        assertThat(results.size(), is(1));

    }

    @Test
    void shouldBeAbleToFindMealByStaringLetters(){

//             Given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

//        When
        List<Meal> results = mealRepository.findByName("Pi", false);

//        Then
        assertThat(results.size(), is(2));

    }

    @Test
    void shouldBeAbleToFindMealByPrice(){

//        Given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

//        When
        List<Meal> results = mealRepository.findByPrice(10);

//        Then
        assertThat(results.size(), is(1));
    }
}
