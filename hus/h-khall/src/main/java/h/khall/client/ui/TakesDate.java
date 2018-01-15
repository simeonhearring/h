package h.khall.client.ui;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Input;

import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.ui.AbstractView;

class TakesDate implements TakesValue<Date>, IsWidget
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
    mInput.setValue(AbstractView.formatDate(mPattern, inValue));
  }

  @Override
  public Date getValue()
  {
    return AbstractView.parseDate(mPattern, mInput.getValue());
  }

  @Override
  public Widget asWidget()
  {
    return mInput;
  }
}