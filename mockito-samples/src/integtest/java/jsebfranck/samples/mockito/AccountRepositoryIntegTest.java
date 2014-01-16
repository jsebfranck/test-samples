package jsebfranck.samples.mockito;

import org.junit.Before;
import org.junit.Test;

public class AccountRepositoryIntegTest {

    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        accountRepository = new AccountRepository();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void createAccount() {
        accountRepository.createAccount(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findAccount() throws Exception {
        accountRepository.findAccount(null);
    }
}
