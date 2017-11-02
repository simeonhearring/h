package h.style.g.client.model;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import h.model.shared.HasBuildTable;
import h.model.shared.Pager;

public class PagerPresenter extends AbstractPresenter<PagerDisplay>
{
  private HasBuildTable mTable;
  private PagerDisplay[] mDisplay;
  private Pager mPager;
  private int[] mMaxPerPageOptions = new int[]
      {
          20, 50, 100
      };

  public PagerPresenter(HasBuildTable inTable, PagerDisplay... inDisplay)
  {
    mTable = inTable;
    mDisplay = inDisplay;
    mPager = new Pager();
    mPager.setMaxPerPage(mMaxPerPageOptions[0]);
  }

  public int getPages()
  {
    return mPager.getPages();
  }

  public void setPageIndex(int inPageNum)
  {
    mPager.setPageIndex(inPageNum);
  }

  public void addPages()
  {
    pagesClear();
    addGoto();
    for (int i = 0; i < getPages(); i++)
    {
      final int pageIndex = i;

      addPage("Page #" + (i + 1), new ClickHandler()
      {
        @Override
        public void onClick(ClickEvent inEvent)
        {
          mPager.setPageIndex(pageIndex);
          mTable.buildTable();
        }
      });
    }
    setPageNum(mPager.getDisplay());
    setShowing(mPager.getShowing());
  }

  public void addMax()
  {
    for (final int value : mMaxPerPageOptions)
    {
      addMax(value, new ClickHandler()
      {
        @Override
        public void onClick(ClickEvent inEvent)
        {
          mPager.setMaxPerPage(value);
          display();
          mTable.buildTable();
        }
      });
    }
    display();
  }

  public void display()
  {
    setMaxNum("Max " + mPager.getMaxPerPage() + " per page");
  }

  public <M> List<M> page(List<M> inList)
  {
    return mPager.page(inList);
  }

  public void previous()
  {
    mPager.previous();
    mTable.buildTable();
  }

  public void next()
  {
    mPager.next();
    mTable.buildTable();
  }

  public void addNextClickHandler(ClickHandler inHandler)
  {
    for (PagerDisplay value : mDisplay)
    {
      value.getNext().addClickHandler(inHandler);
    }
  }

  public void addPrevClickHandler(ClickHandler inHandler)
  {
    for (PagerDisplay value : mDisplay)
    {
      value.getPrev().addClickHandler(inHandler);
    }
  }

  public boolean isPrevSource(ClickEvent inEvent)
  {
    boolean ret = false;
    for (PagerDisplay value : mDisplay)
    {
      ret |= value.getPrev().isSource(inEvent);
    }
    return ret;
  }

  public boolean isNextSource(ClickEvent inEvent)
  {
    boolean ret = false;
    for (PagerDisplay value : mDisplay)
    {
      ret |= value.getNext().isSource(inEvent);
    }
    return ret;
  }

  private void pagesClear()
  {
    for (PagerDisplay value : mDisplay)
    {
      value.pagesClear();
    }
  }

  private void addGoto()
  {
    for (PagerDisplay value : mDisplay)
    {
      value.addGoto();
    }
  }

  private void addPage(String inPage, ClickHandler inHandler)
  {
    for (PagerDisplay value : mDisplay)
    {
      value.addPage(inPage, inHandler);
    }
  }

  private void setPageNum(String inDisplay)
  {
    for (PagerDisplay value : mDisplay)
    {
      value.setPageNum(inDisplay);
    }
  }

  private void addMax(int inMax, ClickHandler inHandler)
  {
    for (PagerDisplay value : mDisplay)
    {
      value.addMax(inMax, inHandler);
    }
  }

  private void setMaxNum(String inText)
  {
    for (PagerDisplay value : mDisplay)
    {
      value.setMaxNum(inText);
    }
  }

  public void setVisible(boolean inVisible)
  {
    for (PagerDisplay value : mDisplay)
    {
      value.setVisible(inVisible);
    }
  }

  public void setShowing(String inText)
  {
    for (PagerDisplay value : mDisplay)
    {
      value.setShowing(inText);
    }
  }
}