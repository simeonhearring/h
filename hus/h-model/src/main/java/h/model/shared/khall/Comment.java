package h.model.shared.khall;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Comment implements Serializable
{
  private Date mOn;
  private String mComment;

  public Date getOn()
  {
    return mOn;
  }

  public void setOn(Date inOn)
  {
    mOn = inOn;
  }

  public String getComment()
  {
    return mComment;
  }

  public void setComment(String inComment)
  {
    mComment = inComment;
  }
}