package h.style.g.client.ui;

import java.util.Date;

import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.base.events.ChangeDateHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.Event;

import h.style.g.client.model.DateSelectDisplay;

public class DateSelectView extends AbstractView implements DateSelectDisplay<ChangeDateHandler>
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, DateSelectView>
  {
  }

  @UiField
  DateTimePicker mDate;

  public DateSelectView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  @Override
  public void setValue(Date inValue)
  {
    mDate.setValue(inValue);
  }

  @Override
  public Date getValue()
  {
    return mDate.getValue();
  }

  @Override
  public void setEnabled(boolean inEnable)
  {
    mDate.setEnabled(inEnable);
  }

  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> inHandler)
  {
    return mDate.addValueChangeHandler(inHandler);
  }

  @Override
  public HandlerRegistration addChangeDateHandler(ChangeDateHandler inHandler)
  {
    return mDate.addChangeDateHandler(inHandler);
  }

  @Override
  public boolean isSource(Event<?> inEvent)
  {
    return mDate == inEvent.getSource();
  }

  @Override
  public void setShow(boolean inShow)
  {
    mDate.setVisible(inShow);
  }

  @Override
  public IsWidget getDate()
  {
    return mDate;
  }

  @Override
  public void setReadOnly(boolean inReadOnly)
  {
    mDate.setReadOnly(inReadOnly);
  }

  @Override
  public void show()
  {
  }

  @Override
  public void hide()
  {
  }
}