package h.model.shared;

import java.util.List;

public class Pager
{
  private int mPageIndex;
  private int mPages;
  private int mMaxPerPage;
  private int mSize;

  public int getPages()
  {
    return mPages;
  }

  public int getMaxPerPage()
  {
    return mMaxPerPage;
  }

  public String getDisplay()
  {
    return "Page " + (mPageIndex + 1) + " of " + mPages;
  }

  public String getShowing()
  {
    return " Count: " + mSize;
  }

  public void setPageIndex(int inIndex)
  {
    mPageIndex = inIndex <= 0 ? 0 : inIndex >= mPages - 1 ? mPages <= 0 ? 0 : mPages - 1 : inIndex;
  }

  protected int getPageIndex()
  {
    return mPageIndex;
  }

  public void setMaxPerPage(int inMaxPerPage)
  {
    mMaxPerPage = inMaxPerPage < 0 ? 0 : inMaxPerPage;
  }

  public boolean isPaging()
  {
    return mMaxPerPage > 0;
  }

  public <M> List<M> page(List<M> inList)
  {
    if (!isPaging())
    {
      return inList;
    }

    mSize = inList.size();

    mPages = mSize / mMaxPerPage + (mSize % mMaxPerPage == 0 ? 0 : 1);

    int begin = mPageIndex * mMaxPerPage;
    int end = begin + mMaxPerPage;
    end = mSize < end ? mSize : end;

    List<M> ret = null;

    ret = inList.subList(begin, end);

    return ret;
  }

  public void previous()
  {
    mPageIndex = mPageIndex == 0 ? mPages - 1 : mPageIndex - 1;
  }

  public void next()
  {
    mPageIndex = mPageIndex == mPages - 1 ? 0 : mPageIndex + 1;
  }
}