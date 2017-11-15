package h.khall.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Student implements Serializable
{
  private List<Part> mParts;
  private List<Hall> mHalls;

  public void normalize()
  {
    if (mParts == null)
    {
      mParts = new ArrayList<>();
    }
    if (mHalls == null)
    {
      mHalls = new ArrayList<>();
    }
  }

  public List<Part> getParts()
  {
    return mParts;
  }

  public void setParts(List<Part> inParts)
  {
    mParts = inParts;
  }

  public List<Hall> getHalls()
  {
    return mHalls;
  }

  public void setHalls(List<Hall> inHalls)
  {
    mHalls = inHalls;
  }
}