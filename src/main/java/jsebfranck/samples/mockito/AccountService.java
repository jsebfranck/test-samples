package jsebfranck.samples.mockito;

import javax.inject.Inject;

/**
 * @author jsebfranck
 */
public class AccountService {

    @Inject
    private AccountRepository accountRepository;

    public void createAccount() {
        accountRepository.createAccount();
    }
}
