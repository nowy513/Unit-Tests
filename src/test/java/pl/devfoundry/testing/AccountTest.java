package pl.devfoundry.testing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreatino(){
//       Given
        Account newAccount = new Account();

//        Then
        assertFalse(newAccount.isActive());
        assertThat(newAccount.isActive()).isFalse();
    }

    @Test
    void accountShouldBeActiveAfterActivation(){
//        Given
        Account newAccount = new Account();

//        When
        newAccount.activate();

//        Then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive()).isTrue();
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaulrDeliveryAddressSet(){

//        Given
        Account account = new Account();

//        When
        Address address = account.getDefaultDeliveryAddress();

//        Then
        assertNull(address);
        assertThat(address).isNull();

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
        assertThat(defaultAddress).isNotNull();

    }
}
