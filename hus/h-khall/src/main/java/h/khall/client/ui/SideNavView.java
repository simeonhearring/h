package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.SideNavPresenter;
import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.AttachEvent.TypeA;
import h.style.g.client.ui.AbstractView;

public class SideNavView extends AbstractView implements SideNavPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, SideNavView>
  {
  }

  @UiField
  Anchor mLogout;

  @UiField
  InlineHTML mUserName, mUserTitle;

  SideNavPresenter mPresenter;

  public SideNavView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new SideNavPresenter(this);
  }

  @UiHandler(
  {
      "mLogout"
  })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mLogout.equals(source))
    {
      fire(new AttachEvent(TypeA.LOGOUT));
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
}