package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PagePresenter;

public class PageView extends AbstractView<PagePresenter> implements PagePresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageView>
  {
  }

  @UiField
  HTMLPanel mTop, mBody, mWrapper;

  @UiField
  SideBarView mSideBar;

  public PageView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mTop.getElement().setAttribute("id", "wrapper");
    mWrapper.getElement().setAttribute("id", "page-wrapper");
    mPresenter = new PagePresenter(this).handlers();
  }

  @Override
  public void sample()
  {
    clear();
    mBody.add(new SampleView());
  }

  @Override
  public void midweek()
  {
    clear();
    mWrapper.setStyleName("gray-bg");
    mSideBar.setVisible(false);
    mBody.add(new MidweekView());
  }

  @Override
  public void participants()
  {
    clear();
    mWrapper.setStyleName("gray-bg");
    mSideBar.setVisible(false);
    mBody.add(new ParticipantView());
  }

  private void clear()
  {
    int ct = mBody.getWidgetCount();
    for (int i = 0; i < ct; i++)
    {
      Widget widget = mBody.getWidget(i);
      widget.removeFromParent();
    }
    // mBody.clear();
  }


  @Override
  public void attach(HasWidgets inPanel)
  {
    inPanel.clear();
    inPanel.add(this);
    ((UIObject) inPanel).removeStyleName("gray-bg");
    // ((UIObject) inPanel).addStyleName("fixed-sidebar");
  }

  @Override
  protected void onLoad()
  {
    metis();
  }
}