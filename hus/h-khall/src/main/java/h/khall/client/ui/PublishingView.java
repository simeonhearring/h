package h.khall.client.ui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

import h.khall.client.model.PublishingPresenter;

public class PublishingView extends AbstractView<PublishingPresenter>
  implements PublishingPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PublishingView>
  {
  }

  @UiField
  DateView mDate;

  public PublishingView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PublishingPresenter(this).handlers();
  }

  @Override
  public HandlerRegistration addChangeHandler(ChangeHandler inHandler)
  {
    return mDate.addChangeHandler(inHandler);
  }

  @Override
  public void setDate(Date inDate)
  {
    mDate.setValue(inDate);
  }

  @Override
  public Date getDate()
  {
    return mDate.getValue();
  }

  public void setLabelVisible(boolean inShow)
  {
    mDate.setLabelVisible(inShow);
  }

  public void setLabel(String inText)
  {
    mDate.setLabel(inText);
  }
}