package pl.devfoundry.testing.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pl.devfoundry.testing.account.Account;
import pl.devfoundry.testing.account.Address;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@Tag("fries")
class AccountTest {



    @Test
    void newAccountShouldNotBeActiveAfterCreatino(){
//       Given
        Account newAccount = new Account();

//        Then
        assertFalse(newAccount.isActive());
//        assertThat(newAccount.isActive()).isFalse();
        assertThat(newAccount.isActive(), equalTo(false));
    }


    @Test
    void accountShouldBeActiveAfterActivation(){
//        Given
        Account newAccount = new Account();

//        When
        newAccount.activate();

//        Then
        assertTrue(newAccount.isActive());
//        assertThat(newAccount.isActive()).isTrue();
        assertThat(newAccount.isActive(), equalTo(true));
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaulrDeliveryAddressSet(){

//        Given
        Account account = new Account();

//        When
        Address address = account.getDefaultDeliveryAddress();

//        Then
        assertNull(address);
//        assertThat(address).isNull();


    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){

//        Given
        Address address = new Address("Krakwska", "67c");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

//        When
        Address defaultAddress = account.getDefaultDeliveryAddress();

//        Then
        assertNotNull(defaultAddress);
//        assertThat(defaultAddress).isNotNull();
    }

    @RepeatedTest(5)
    void newAccountWithNotNullAddressShouldBeActive(){

//        Given
        Address address = new Address("Puławska", "46/6");

//        When
        Account account = new Account(address);

//        Then
        assumingThat(address != null, () -> {
                assertTrue(account.isActive());
    });
    }

    @Test
    void invalidEmailShouldThrowException(){

//        Given
        Account account = new Account();

//        When
//        Then
        assertThrows(IllegalArgumentException.class, () -> account.setEmail("wrongEmail"));
    }

    @Test
    void vaildEmailShouldBeSet(){

//        Given
        Account account = new Account();

//        When
        account.setEmail("kontakt@devfoundry.pl");
//        Then
        assertThat(account.getEmail(), is("kontakt@devfoundry.pl"));
    }
}
