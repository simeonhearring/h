package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Badge;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Pager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.LabelDisplay;
import h.style.g.client.model.NoticeDisplay;

public class NoticeView extends AbstractView implements NoticeDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, NoticeView>
  {
  }

  @UiField
  Heading mHeading;

  @UiField
  LabelView mMessage;

  @UiField
  Pager mPager;

  @UiField
  Badge mBadge;

  public NoticeView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  @Override
  public void setHeading(String inHeading)
  {
    mHeading.setText(inHeading);
  }

  @Override
  public LabelDisplay getMessage()
  {
    return mMessage;
  }

  @Override
  public void addNextClickHandler(ClickHandler inHandler)
  {
    mPager.addNextClickHandler(inHandler);
  }

  @Override
  public void addPreviousClickHandler(ClickHandler inHandler)
  {
    mPager.addPreviousClickHandler(inHandler);
  }

  @Override
  public void setRead(boolean inRead)
  {
    mBadge.setVisible(inRead);
  }

  @Override
  public void setPagerVisible(boolean inVisible)
  {
    mPager.setVisible(inVisible);
  }
}