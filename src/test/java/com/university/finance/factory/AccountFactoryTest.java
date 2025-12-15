package com.university.finance.factory;
import com.university.finance.model.Account;
import com.university.finance.model.User;
import com.university.finance.pattern.factory.AccountFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountFactoryTest {

    @Test
    void shouldCreateAccountWithInitialBalance() {
        User user = new User("1", "Ali", "a@mail.com", "0600");
        Account account = AccountFactory.createAccount(user, 500.0);

        assertEquals(500.0, account.getBalance());
        assertEquals(user, account.getOwner());
    }

    @Test
    void shouldGenerateAccountNumber() {
        User user = new User("1", "Ali", "a@mail.com", "0600");
        Account account = AccountFactory.createAccount(user, 100.0);

        assertNotNull(account.getAccountNumber());
    }
}
