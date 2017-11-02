package h.style.g.client.model;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import h.model.shared.Notice;

public class NoticePresenter extends AbstractPresenter<NoticeDisplay> implements ClickHandler
{
  private List<Notice> mNotices;
  private int mCurrent;
  private ModalDisplay mModal;

  public NoticePresenter(NoticeDisplay inDisplay, List<Notice> inNotices)
  {
    initDisplay(inDisplay);
    mNotices = inNotices;

    mDisplay.addNextClickHandler(nexthandler());
    mDisplay.addPreviousClickHandler(previoushandler());
    mDisplay.setPagerVisible(mNotices.size() > 1);

    setUpNotice();
  }

  public Date getNoticeDate()
  {
    return mNotices.get(mCurrent).getDate();
  }

  protected void setUpNotice()
  {
    Notice notice = mNotices.get(mCurrent);
    mDisplay.setHeading(notice.getTitle());
    mDisplay.getMessage().setHTML(notice.getMessage());
  }

  private ClickHandler nexthandler()
  {
    return new ClickHandler()
    {
      @Override
      public void onClick(ClickEvent inEvent)
      {
        next();
      }
    };
  }

  private ClickHandler previoushandler()
  {
    return new ClickHandler()
    {
      @Override
      public void onClick(ClickEvent inEvent)
      {
        previous();
      }
    };
  }

  public void show()
  {
    mModal = mDisplay.getModal();
    mModal.getHeader().setText("What's New");
    mModal.setCheckText("Do not show again");
    mModal.setCheckVisible(true);
    mModal.getSave().setText("Close");
    mModal.getSave().addClickHandler(this);
    mModal.getClose().setVisible(false);
    mModal.addBody(mDisplay);
    mModal.setShow(true);
  }

  @Override
  public void onClick(ClickEvent inEvent)
  {
    if (mModal.getSave().isSource(inEvent))
    {
      if (mModal.isChecked())
      {
        saved();
      }
      close();
    }
  }

  protected void close()
  {
    mModal.setShow(false);
  }

  protected void saved()
  {
  }

  private void next()
  {
    mCurrent = mNotices.size() == mCurrent + 1 ? 0 : mCurrent + 1;
    setUpNotice();
  }

  private void previous()
  {
    mCurrent = mCurrent == 0 ? mNotices.size() - 1 : mCurrent - 1;
    setUpNotice();
  }
}