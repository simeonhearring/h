package h.khall.shared.model;

import java.io.Serializable;

import h.model.shared.khall.Profile;

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

  public boolean isAuthenticated(Profile inProfile)
  {
    // TODO
    return true;
  }
}