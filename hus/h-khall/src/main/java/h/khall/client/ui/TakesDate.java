package h.khall.client.ui;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Input;

import com.google.gwt.user.client.TakesValue;

class TakesDate implements TakesValue<Date>
{
  private String mPattern = "yyyy-MM";

  private Input mInput;

  public TakesDate(Input inInput)
  {
    mInput = inInput;
  }

  @Override
  public void setValue(Date inValue)
  {
    mInput.setValue(MinistryView.formatDate(mPattern, inValue));
  }

  @Override
  public Date getValue()
  {
    return MinistryView.parseDate(mPattern, mInput.getValue());
  }
}