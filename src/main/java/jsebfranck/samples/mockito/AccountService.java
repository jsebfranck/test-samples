package jsebfranck.samples.mockito;

import javax.inject.Inject;

/**
 * @author jsebfranck
 */
public class AccountService {

  @Inject
  private AccountRepository accountRepository;

  public Account getAccountByLogin(String login) {
    try {
      return accountRepository.findAccount(login);
    } catch (EntityNotFoundException enfe) {
      return null;
    }
  }

  public void createAccount(String login, String password) throws ServiceException {
    if (login == null) {
      throw new ServiceException("Account field is mandatory");
    }

    if (password == null) {
      throw new ServiceException("Password field is mandatory");
    }

    if (getAccountByLogin(login) != null) {
      throw new ServiceException(String.format("The account %s already exists", login));
    }

    Account account = new Account(login, password);
    accountRepository.createAccount(account);
  }
}
