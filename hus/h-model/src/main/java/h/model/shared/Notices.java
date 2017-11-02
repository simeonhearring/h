package h.model.shared;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Notices implements Serializable
{
  private List<Notice> mNotices;

  public List<Notice> getNotices()
  {
    return mNotices;
  }
}