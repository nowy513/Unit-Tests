package pl.devfoundry.testing;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void simulateLargeOrder() {

//        Given
        Cart cart = new Cart();

//        When
//        Then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }
}