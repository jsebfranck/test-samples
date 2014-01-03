package jsebfranck.samples.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * @see AccountService
 * @author jsebfranck
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService = new AccountService();

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void firstTest() {

        // When
        accountService.createAccount();

        // Then
        verify(accountRepository).createAccount();
    }
}
