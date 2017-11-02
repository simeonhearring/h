package h.model.shared;

import java.io.Serializable;
import java.util.Date;

/*
 * Used in xStream
 */
@SuppressWarnings("serial")
public class Acknowledge implements Serializable
{
  private Date mDate;

  Acknowledge()
  {
  }

  public Acknowledge(Date inDate)
  {
    mDate = inDate;
  }

  public Date getDate()
  {
    return mDate;
  }

  public void setDate(Date inDate)
  {
    mDate = inDate;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (mDate == null ? 0 : mDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object inObj)
  {
    if (this == inObj)
    {
      return true;
    }
    if (inObj == null)
    {
      return false;
    }
    if (getClass() != inObj.getClass())
    {
      return false;
    }
    Acknowledge other = (Acknowledge) inObj;
    if (mDate == null)
    {
      if (other.mDate != null)
      {
        return false;
      }
    }
    else if (!mDate.equals(other.mDate))
    {
      return false;
    }
    return true;
  }
}