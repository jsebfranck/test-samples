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
    Account account = new Account();
    account.setLogin(login);
    account.setPassword(password);

    checkAccountParameters(account);

    try {
      accountRepository.createAccount(account);
    } catch (EntityAlreadyExistsException eaee) {
      throw new ServiceException(String.format("The account %s already exists", login));
    }
  }

  void checkAccountParameters(Account account) throws ServiceException {
    if (account.getLogin() == null) {
      throw new ServiceException("Account field is mandatory");
    }

    if (account.getPassword() == null) {
      throw new ServiceException("Password field is mandatory");
    }
  }
}
