package h.model.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Profile implements Serializable
{
  private String mUserName;
  private String mUserId;
  private String mUserTitle;
  private String mPassword;

  public String getUserName()
  {
    return mUserName;
  }

  public void setUserName(String inUserName)
  {
    mUserName = inUserName;
  }

  public void setUserId(String inUserId)
  {
    mUserId = inUserId;
  }

  public String getUserId()
  {
    return mUserId;
  }

  public String getUserTitle()
  {
    return mUserTitle;
  }

  public void setUserTitle(String inUserTitle)
  {
    mUserTitle = inUserTitle;
  }

  public String getPassword()
  {
    return mPassword;
  }

  public void setPassword(String inPassword)
  {
    mPassword = inPassword;
  }
}