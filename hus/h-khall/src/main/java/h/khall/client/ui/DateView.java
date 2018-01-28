package h.khall.client.ui;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.constants.InputType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class DateView extends h.style.g.client.ui.AbstractView implements TakesValue<Date>
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, DateView>
  {
  }

  @UiField
  Heading mLabel;

  @UiField
  Input mDate;

  private TakesDate mDateV;

  public DateView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mDateV = new TakesDate(mDate);
  }

  public HandlerRegistration addChangeHandler(ChangeHandler inHandler)
  {
    return mDate.addChangeHandler(inHandler);
  }

  public void setType(InputType inType)
  {
    // DATETIME("datetime"),
    // DATETIME_LOCAL("datetime-local"),
    // DATE("date"),
    // MONTH("month"),
    // TIME("time"),
    // WEEK("week"),
    mDate.setType(inType);
  }

  public void setPattern(String inPattern)
  {
    mDateV.setPattern(inPattern);
  }

  @Override
  public void setValue(Date inDate)
  {
    mDateV.setValue(inDate);
  }

  @Override
  public Date getValue()
  {
    return mDateV.getValue();
  }

  public void setLabelVisible(boolean inShow)
  {
    mLabel.setVisible(inShow);
  }

  public HasText getLabel()
  {
    return mLabel;
  }

  public void setLabel(String inText)
  {
    mLabel.setText(inText);
  }
}