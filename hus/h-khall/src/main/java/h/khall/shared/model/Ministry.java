package h.khall.shared.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Ministry implements Serializable
{
  private Boolean mPublisher;
  private Date mPublishing;
  private List<Report> mReports;

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

  public Boolean getPublisher()
  {
    return mPublisher;
  }

  public void setPublisher(Boolean inPublisher)
  {
    mPublisher = inPublisher;
  }

  public String getStatus()
  {
    return null;
  }
}