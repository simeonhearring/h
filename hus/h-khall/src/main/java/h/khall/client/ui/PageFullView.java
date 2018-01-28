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

import h.khall.client.model.pages.PageFullPresenter;
import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.AttachEvent.TypeA;

public class PageFullView extends AbstractView<PageFullPresenter>
  implements PageFullPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageFullView>
  {
  }

  @UiField
  HTMLPanel mTop;

  @UiField
  Anchor mBars, mLogout;

  public PageFullView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mTop.getElement().setAttribute("id", "wrapper");
    mPresenter = new PageFullPresenter(this);
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
      fire(new AttachEvent(TypeA.LOGOUT));
    }
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
}