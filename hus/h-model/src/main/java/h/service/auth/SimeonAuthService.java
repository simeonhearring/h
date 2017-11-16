package h.service.auth;

public class SimeonAuthService implements AuthService
{
  @Override
  public String authenticate(String inToken)
  {
    return inToken;
  }
}
