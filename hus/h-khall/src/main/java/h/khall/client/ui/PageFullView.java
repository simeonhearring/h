package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PageFullPresenter;
import h.style.g.client.ui.AbstractView;

public class PageFullView extends AbstractView implements PageFullPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageFullView>
  {
  }

  @UiField
  HTMLPanel mTop;

  @UiField
  Anchor mBars, mLogoutA, mLogoutB;

  @UiField
  InlineHTML mUserName, mUserTitle;

  PageFullPresenter mPresenter;

  public PageFullView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mTop.getElement().setAttribute("id", "wrapper");
    mPresenter = new PageFullPresenter(this);
  }

  @UiHandler(
  {
      "mBars", "mLogoutA", "mLogoutB"
  })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mBars.equals(source))
    {
      toggleNavBar();
    }
    else if (mLogoutA.equals(source) || mLogoutB.equals(source))
    {
      mPresenter.logout();
    }
  }

  @Override
  public void setUserName(String inUserName)
  {
    mUserName.setHTML(SafeHtmlUtils.fromString(inUserName));
  }

  @Override
  public void setUserTitle(String inUserTitle)
  {
    mUserTitle.setHTML(SafeHtmlUtils.fromString(inUserTitle));
  }

  @Override
  public void attach(HasWidgets inPanel)
  {
    inPanel.clear();
    inPanel.add(this);
    ((UIObject) inPanel).removeStyleName("gray-bg");
    ((UIObject) inPanel).addStyleName("fixed-sidebar no-skin-config full-height-layout");
  }

  @Override
  protected void onLoad()
  {
    metis();
    slimScroll();
  }

  private static native void metis()
  /*-{
		$wnd.jQuery('#side-menu').metisMenu().show();
  }-*/;

  private static native void toggleNavBar()
  /*-{
		$wnd.jQuery('body').toggleClass('mini-navbar');
		$wnd.SmoothlyMenu();
  }-*/;
}