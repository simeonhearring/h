package h.model.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Login implements Serializable
{
  private Profile mProfile;
  private Client mClient;

  public Profile getProfile()
  {
    return mProfile;
  }
  public void setProfile(Profile inProfile)
  {
    mProfile = inProfile;
  }
  public Client getClient()
  {
    return mClient;
  }
  public void setClient(Client inClient)
  {
    mClient = inClient;
  }
}