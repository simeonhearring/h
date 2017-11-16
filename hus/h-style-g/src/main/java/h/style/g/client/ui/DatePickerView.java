package h.style.g.client.ui;

import java.util.Date;

import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ChangeDateHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.Event;

import h.style.g.client.model.DateSelectDisplay;

public class DatePickerView extends AbstractView implements DateSelectDisplay<ChangeDateHandler>
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, DatePickerView>
  {
  }

  @UiField
  DatePicker mDate;

  public DatePickerView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  public DatePickerView(DatePicker inDate)
  {
    mDate = inDate;
  }

  @Override
  public void setValue(Date inValue)
  {
    mDate.setValue(inValue);
  }

  @Override
  public void show()
  {
    mDate.show();
  }

  @Override
  public void hide()
  {
    mDate.hide();
  }

  @Override
  public Date getValue()
  {
    return mDate.getValue();
  }

  @Override
  public void setWidth(String inWidth)
  {
    mDate.setWidth(inWidth);
  }

  @Override
  public void setEnabled(boolean inEnable)
  {
    mDate.setEnabled(inEnable);
  }

  @Override
  public void setReadOnly(boolean inReadOnly)
  {
    mDate.setReadOnly(inReadOnly);
  }

  @Override
  public IsWidget getDate()
  {
    return mDate;
  }

  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> inHandler)
  {
    return addValueChangeHandler(mDate, inHandler);
  }

  public HandlerRegistration setValueChangeHandler(ValueChangeHandler<Date> inHandler)
  {
    return setValueChangeHandler(mDate, inHandler);
  }

  @Override
  public boolean isSource(Event<?> inEvent)
  {
    return mDate.equals(inEvent.getSource());
  }

  @Override
  public void setShow(boolean inShow)
  {
    mDate.setVisible(inShow);
  }

  @Override
  public HandlerRegistration addChangeDateHandler(ChangeDateHandler inHandler)
  {
    return register(mDate.addChangeDateHandler(inHandler));
  }
}