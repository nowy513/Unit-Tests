package pl.devfoundry.testing.homeword;

import org.junit.jupiter.api.Test;
import pl.devfoundry.testing.homework.Coordinates;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinatesTest {

    @Test
    void constructorShouldNotBeValueAbove100(){

//        Then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(101, 0));
    }

    @Test
    void constructorShouldNotBeValueBelow0(){

//        Then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-1, 0));

    }

    @Test
    void copyShouldReturnAddCoordinates(){

//        Given
        Coordinates coordinates = new Coordinates(10, 10);

//        When
        Coordinates copy = Coordinates.copy(coordinates, 5, 5);

//        Then
        assertThat(copy.getX(), equalTo(15));
        assertThat(copy.getY(), equalTo(15));

    }

    @Test
    void copyShouldReturnNewObject(){

//        Given
        Coordinates coordinates = new Coordinates(10, 10);

//        When
        Coordinates copy = Coordinates.copy(coordinates, 0, 0);

//        Then
        assertThat(copy, not(sameInstance(coordinates)));
        assertThat(copy, equalTo(coordinates));

    }
}
