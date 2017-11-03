package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PagePresenter;
import h.style.g.client.ui.AbstractView;

public class PageView extends AbstractView implements PagePresenter.Display, ScheduledCommand
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageView>
  {
  }

  @UiField
  Anchor mBars;

  PagePresenter mPresenter;

  public PageView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PagePresenter(this);
  }

  @UiHandler(
      {
        "mBars"
      })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mBars.equals(source))
    {
      toggleNavBar();
    }
  }

  @Override
  public void attach(HasWidgets inPanel)
  {
    inPanel.clear();
    inPanel.add(this);
    Scheduler.get().scheduleDeferred(this);
  }

  private static native void toggleNavBar()
  /*-{
		$wnd.$('body').toggleClass('mini-navbar');
		$wnd.SmoothlyMenu();
  }-*/;

  @Override
  public void execute()
  {
  }
}