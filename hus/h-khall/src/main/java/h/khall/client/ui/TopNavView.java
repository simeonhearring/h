package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.TopNavPresenter;

public class TopNavView extends AbstractView<TopNavPresenter> implements TopNavPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, TopNavView>
  {
  }

  @UiField
  Anchor mBars, mLogout;

  public TopNavView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new TopNavPresenter(this);
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
}