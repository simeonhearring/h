package h.model.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Profile implements Serializable
{
  public enum Type
  {
    USER;
  }

  private Type mType;

  private String mUserName;
  private String mUserId;
  private String mUserTitle;
  private String mPassword;

  private String mFirst, mLast;

  public String gUserName()
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

  public String gPassword()
  {
    return mPassword;
  }

  public void setPassword(String inPassword)
  {
    mPassword = inPassword;
  }

  public String getLast()
  {
    return mLast;
  }

  public void setLast(String inLast)
  {
    mLast = inLast;
  }

  public String getFirst()
  {
    return mFirst;
  }

  public void setFirst(String inFirst)
  {
    mFirst = inFirst;
  }

  public Type getType()
  {
    return mType;
  }

  public void setType(Type inType)
  {
    mType = inType;
  }

  public String gLocator()
  {
    return mUserId;
  }
}