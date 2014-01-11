package jsebfranck.samples.mockito;

public class Account {
  
  private final String login;
  private final String password;

  public Account(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }
}
