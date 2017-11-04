package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PagePresenter;
import h.style.g.client.ui.AbstractView;

public class PageView extends AbstractView implements PagePresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageView>
  {
  }

  @UiField
  HTMLPanel mTop;

  @UiField
  Anchor mBars, mLogout;

  PagePresenter mPresenter;

  public PageView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mTop.getElement().setAttribute("id", "wrapper");
    mPresenter = new PagePresenter(this);
  }

  @UiHandler(
  {
      "mBars", "mLogout"
  })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mBars.equals(source))
    {
      toggleNavBar();
    }
    else if (mLogout.equals(source))
    {
      mPresenter.logout();
    }
  }

  @Override
  public void attach(HasWidgets inPanel)
  {
    inPanel.clear();
    inPanel.add(this);
    ((UIObject) inPanel).removeStyleName("gray-bg");
  }

  private static native void toggleNavBar()
  /*-{
  	$wnd.$('body').toggleClass('mini-navbar');
  	$wnd.SmoothlyMenu();
  }-*/;
}