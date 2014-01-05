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
   * - argument capture : OK
   * - doThrow : OK
   * - spy : OK
   * - verifyNoMoreInteractions : OK
   * - any : OK
   * - void method : OK
   */

  private static final String LOGIN = "login";
  private static final String PASSWORD = "password";

  @InjectMocks
  @Spy
  private AccountService accountService;

  @Mock
  private AccountRepository accountRepository;

  @Test
  public void getAccountByLogin_withExistingAccount_shouldReturnTheAccount() throws Exception {
    // Given
    Account account = new Account();
    doReturn(account).when(accountRepository).findAccount(LOGIN);

    // When
    Account result = accountService.getAccountByLogin(LOGIN);

    // Then
    assertEquals(account, result);
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
  public void createAccount_nominalCase_shouldCreateTheAccount()
      throws Exception {
    // When
    accountService.createAccount(LOGIN, PASSWORD);

    // Then
    ArgumentCaptor<Account> argument = ArgumentCaptor.forClass(Account.class);
    verify(accountRepository).createAccount(argument.capture());
    Account createdAccount = argument.getValue();
    assertEquals(LOGIN, createdAccount.getLogin());
    assertEquals(PASSWORD, createdAccount.getPassword());

    verifyNoMoreInteractions(accountRepository);

    verify(accountService).checkAccountParameters(any(Account.class));
  }

  @Test(expected = ServiceException.class)
  public void createAccount_withAnExistingAccount_shouldThrowsServiceException() throws Exception {
    // Given
    doThrow(new EntityAlreadyExistsException()).when(accountRepository).createAccount(any(Account.class));

    // When
    accountService.createAccount(LOGIN, PASSWORD);

    // Then assert that a ServiceException is thrown
  }

  @Test
  public void checkAccountParameters_withCorrectAccount_shouldDoNothing() throws Exception {
    // Given
    Account account = createAccountWithCorrectParameters();

    // When
    accountService.checkAccountParameters(account);

    // Then
  }

  @Test(expected = ServiceException.class)
  public void checkAccountParameters_withNullLogin_shouldThrowsServiceException() throws Exception {
    // Given
    Account account = createAccountWithCorrectParameters();
    account.setLogin(null);

    // When
    accountService.checkAccountParameters(account);

    // Then assert that a ServiceException is thrown
  }

  @Test(expected = ServiceException.class)
  public void checkAccountParameters_withNullPassword_shouldThrowsServiceException() throws Exception {
    // Given
    Account account = createAccountWithCorrectParameters();
    account.setPassword(null);

    // When
    accountService.checkAccountParameters(account);

    // Then assert that a ServiceException is thrown
  }

  private Account createAccountWithCorrectParameters() {
    Account account = new Account();
    account.setLogin(LOGIN);
    account.setPassword(PASSWORD);
    return account;
  }
}
