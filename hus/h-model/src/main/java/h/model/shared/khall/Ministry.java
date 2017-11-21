package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Ministry implements Serializable
{
  private Date mPublishing;
  private List<Report> mReports;

  public void normalize()
  {
    if (mReports == null)
    {
      mReports = new ArrayList<>();
    }
  }

  public List<Report> getReports()
  {
    return mReports;
  }

  public void setReports(List<Report> inReports)
  {
    mReports = inReports;
  }

  public Date getPublishing()
  {
    return mPublishing;
  }

  public void setPublishing(Date inPublishing)
  {
    mPublishing = inPublishing;
  }
}