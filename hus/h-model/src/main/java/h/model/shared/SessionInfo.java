package h.model.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public abstract class SessionInfo implements Serializable
{
  private String mEnvironment;
  private String mVersion;
  private String mHostName;
  private String mRemoteHost;

  private Map<String, String> mProperties;

  private Profile mProfile;

  public String getUserInfo()
  {
    String ret = null;
    if (mProfile != null)
    {
      ret = mProfile.getUserId() + "-" + mProfile.getUserName();
    }
    return ret;
  }

  public Profile getProfile()
  {
    return mProfile;
  }

  public void setProfile(Profile inProfile)
  {
    mProfile = inProfile;
  }

  public String getEnvironment()
  {
    return mEnvironment;
  }

  public void setEnvironment(String inEnvironment)
  {
    mEnvironment = inEnvironment;
  }

  public String getVersion()
  {
    return mVersion;
  }

  public void setVersion(String inVersion)
  {
    mVersion = inVersion;
  }

  public String getHostName()
  {
    return mHostName;
  }

  public void setHostName(String inHostName)
  {
    mHostName = inHostName;
  }

  public String getRemoteHost()
  {
    return mRemoteHost;
  }

  public void setRemoteHost(String inRemoteHost)
  {
    mRemoteHost = inRemoteHost;
  }

  public Map<String, String> getProperties()
  {
    return mProperties;
  }

  public void setProperties(Map<String, String> inProperties)
  {
    mProperties = inProperties;
  }

  public String get(String inName)
  {
    return mProperties.get(inName);
  }

  public String value(String inKey)
  {
    if (!mProperties.containsKey(inKey))
    {
      return null;
    }
    return get(inKey);
  }

  public boolean contains(String inKey, String... inValues)
  {
    if (!mProperties.containsKey(inKey))
    {
      return false;
    }

    String keyvalue = get(inKey);

    return isEqualOneOf(keyvalue, inValues);
  }

  private static boolean isEqualOneOf(String inValue, String... inValues)
  {
    boolean ret = false;
    for (String value : inValues)
    {
      ret |= value != null && value.equals(inValue);
    }
    return ret;
  }

  public List<Notice> getNotices()
  {
    return new ArrayList<>();
  }

  public abstract String getMessagePath();

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("SessionInfo [mEnvironment=");
    builder.append(mEnvironment);
    builder.append(", mVersion=");
    builder.append(mVersion);
    builder.append(", mHostName=");
    builder.append(mHostName);
    builder.append(", mRemoteHost=");
    builder.append(mRemoteHost);
    builder.append("]");
    return builder.toString();
  }
}