package h.khall.client.ui;

import static h.style.g.client.ui.AbstractView.formatDate;
import static h.style.g.client.ui.AbstractView.parseDate;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Input;

import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

class TakesDate implements TakesValue<Date>, IsWidget
{
  private String mPattern = "yyyy-MM";

  private Input mInput;

  public TakesDate(Input inInput)
  {
    mInput = inInput;
  }

  public void setPattern(String inPattern)
  {
    mPattern = inPattern;
  }

  @Override
  public void setValue(Date inValue)
  {
    mInput.setValue(formatDate(mPattern, inValue));
  }

  @Override
  public Date getValue()
  {
    String value = mInput.getValue();
    return parseDate(mPattern, value);
  }

  @Override
  public Widget asWidget()
  {
    return mInput;
  }
}