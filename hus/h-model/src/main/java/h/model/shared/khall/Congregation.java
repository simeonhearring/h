package h.model.shared.khall;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Congregation implements Serializable
{
  private Integer mId;
  private String mNumber;
  private String mName;
  private Date mMidweekOn;
  private Hall[] mHalls;

  public Integer getId()
  {
    return mId;
  }

  public void setId(Integer inId)
  {
    mId = inId;
  }

  public Date getMidweekOn()
  {
    return mMidweekOn;
  }

  public void setMidweekOn(Date inMidweekOn)
  {
    mMidweekOn = inMidweekOn;
  }

  public String getNumber()
  {
    return mNumber;
  }

  public void setNumber(String inNumber)
  {
    mNumber = inNumber;
  }

  public String getName()
  {
    return mName;
  }

  public void setName(String inName)
  {
    mName = inName;
  }

  public Hall[] getHalls()
  {
    return mHalls;
  }

  public void setHalls(Hall... inHalls)
  {
    mHalls = inHalls;
  }
}