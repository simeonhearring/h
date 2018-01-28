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

  public static boolean isAuthenticated(Profile inServer, Profile inClient)
  {
    boolean ret = false;
    if (inServer != null)
    {
      ret = inClient.gPassword().equals(inServer.gPass());
      inServer.setEncrypt(inClient.gEncrypt());
      inServer.setPassword(null);
      inServer.setUserName(inServer.getFirst() + " " + inServer.getLast());
    }
    inClient.setPassword(null);
    return ret;
  }

  public boolean isAuthenticated()
  {
    return mProfile != null;
  }
}