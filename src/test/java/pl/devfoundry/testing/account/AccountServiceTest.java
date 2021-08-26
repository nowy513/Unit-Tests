package pl.devfoundry.testing.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    void getAllActiveAccounts() {

//        Given
        AccountRepository accountRepositoryStub = new AccountRepositoryStub();
        AccountService accountService = new AccountService(accountRepositoryStub);

//        When
        List<Account> accountList = accountService.getAllActiveAccounts();

//        Then
        assertThat(accountList, hasSize(2));
    }
}