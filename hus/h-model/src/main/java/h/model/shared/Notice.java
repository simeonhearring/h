package h.model.shared;

import java.io.Serializable;
import java.util.Date;

/*
 * Used in xStream
 */
public class Notice implements Serializable
{
  private static final long serialVersionUID = -3835859036132943737L;

  private Date mDate;
  private String mTitle;
  private String mMessage;

  Notice()
  {
  }

  public Notice(Date inDate, String inTitle, String inMessage)
  {
    mDate = inDate;
    mTitle = inTitle;
    mMessage = inMessage;
  }

  public Date getDate()
  {
    return mDate;
  }

  public void setDate(Date inDate)
  {
    mDate = inDate;
  }

  public String getTitle()
  {
    return mTitle;
  }

  public void setTitle(String inTitle)
  {
    mTitle = inTitle;
  }

  public String getMessage()
  {
    return mMessage;
  }

  public void setMessage(String inMessage)
  {
    mMessage = inMessage;
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
    Notice other = (Notice) inObj;
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