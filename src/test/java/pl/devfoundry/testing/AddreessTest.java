package pl.devfoundry.testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

 class AddreessTest {

    @ParameterizedTest
    @CsvSource({"Fabryczna, 10", "Armi Krajowej, 57/11", "'Romka, Tomka, Atomka', 40"})
    void givenAddressesShouldNotBeEmptyAndHaveProperyNames(String street, String number){
        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
    }

    @ParameterizedTest
     @CsvFileSource(resources = "/addresses.csv")
    void addressesFromFileShouldNotBeEmptyAndHaveProperyNames(String street, String number){
        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
    }
}
