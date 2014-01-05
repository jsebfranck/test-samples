package jsebfranck.samples.mockito;

/**
 * @author jsebfranck
 */
public class AccountRepository {

    public void createAccount(Account account) throws EntityAlreadyExistsException {
      throw new UnsupportedOperationException("not implemented yet");
    }

    public Account findAccount(String login) throws EntityNotFoundException {
      throw new UnsupportedOperationException("not implemented yet");
    }
}
