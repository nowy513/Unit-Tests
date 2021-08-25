package pl.devfoundry.testing.homeword;

import org.junit.jupiter.api.Test;
import pl.devfoundry.testing.homework.Cargo;
import pl.devfoundry.testing.homework.Coordinates;
import pl.devfoundry.testing.homework.Unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnitTest {

    @Test
    void unitShouldNotMoveWithoutFuel() {

        //given
        Unit unit = new Unit(new Coordinates(0,0),0,10);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.move(2,2));
    }

    @Test
    void unitShouldLoseFuelWhenMoving(){

//        Given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);

//        When
        unit.move(2,2);

//        Then
        assertThat(unit.getFuel(), is(6));
    }

    @Test
    void movedUnitShouldReturnNewCoordinates(){

//        Given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);

//        When
        Coordinates move = unit.move(2, 2);

//        Then

        assertThat(move, equalTo(new Coordinates(2, 2)));
    }

    @Test
    void fuelingShouldNotExceedMaxFuelLimit(){

//        Given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);

//        When
        unit.tankUp();

//        Then
        assertThat(unit.getFuel(), is(10));
    }

    @Test
    void cargoNotExceedMaxWeightLimit(){

//        Given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);

        Cargo c1 = new Cargo("c1", 5);
        Cargo c2 = new Cargo("c2", 6);

//        When
        unit.loadCargo(c1);

//        Then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(c2));
    }

    @Test
    void unloadingAllCargoShouldReduceUnitLoadToZero(){

//        Given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);

        Cargo c1 = new Cargo("c1", 5);
        Cargo c2 = new Cargo("c2", 5);

//        When
        unit.unloadAllCargo();

//        Then
        assertThat(unit.getLoad(), is(0));
    }
}
