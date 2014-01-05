package jsebfranck.samples.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @see AccountService
 * @author jsebfranck
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  /*
   * Mockito features to test
   * 
   * - mock creation : OK
   * - doReturn : OK
   * - verifyNoMoreInteractions : OK
   * - doThrow : OK
   * - argument capture : OK
   * - spy : OK
   * - verify with any : TODO
   * - verify with
   * parameters : OK
   * - void method : OK
   */

  private static final String LOGIN = "login";
  private static final String PASSWORD = "password";
  private static final Account EXISTING_ACCOUNT = new Account(LOGIN, PASSWORD);

  @InjectMocks
  @Spy
  private AccountService accountService;

  @Mock
  private AccountRepository accountRepository;

  @Test
  public void getAccountByLogin_withExistingAccount_shouldReturnTheAccount() throws Exception {
    // Given
    doReturn(EXISTING_ACCOUNT).when(accountRepository).findAccount(LOGIN);

    // When
    Account result = accountService.getAccountByLogin(LOGIN);

    // Then
    assertEquals(EXISTING_ACCOUNT, result);
    verify(accountRepository).findAccount(LOGIN);
    verifyNoMoreInteractions(accountRepository);
  }

  @Test
  public void getAccountByLogin_withUnexistingAccount_shouldReturnNull() throws Exception {
    // Given
    doThrow(new EntityNotFoundException()).when(accountRepository).findAccount(LOGIN);

    // When
    Account result = accountService.getAccountByLogin(LOGIN);

    // Then
    assertNull(result);
  }

  @Test
  public void createAccount_nominalCase_shouldCreateTheAccount() throws Exception {
    // Given
    doReturn(null).when(accountService).getAccountByLogin(LOGIN);

    // When
    accountService.createAccount(LOGIN, PASSWORD);

    // Then
    ArgumentCaptor<Account> argument = ArgumentCaptor.forClass(Account.class);
    verify(accountRepository).createAccount(argument.capture());
    Account createdAccount = argument.getValue();
    assertEquals(LOGIN, createdAccount.getLogin());
    assertEquals(PASSWORD, createdAccount.getPassword());

    verifyNoMoreInteractions(accountRepository);

    verify(accountService).getAccountByLogin(LOGIN);
  }

  @Test(expected = ServiceException.class)
  public void createAccount_withAnExistingAccount_shouldThrowsServiceException() throws Exception {
    // Given
    doReturn(EXISTING_ACCOUNT).when(accountService).getAccountByLogin(LOGIN);

    // When
    accountService.createAccount(LOGIN, PASSWORD);

    // Then assert that a ServiceException is thrown
  }

  @Test(expected = ServiceException.class)
  public void createAccount_withNullLogin_shouldThrowsServiceException() throws Exception {
    // When
    accountService.createAccount(null, PASSWORD);

    // Then assert that a ServiceException is thrown
  }

  @Test(expected = ServiceException.class)
  public void createAccount_withNullPassword_shouldThrowsServiceException() throws Exception {
    // When
    accountService.createAccount(LOGIN, null);

    // Then assert that a ServiceException is thrown
  }
}
