package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.ListBox;

import com.google.gwt.user.client.TakesValue;

class TakesPartial implements TakesValue<Double>
{
  private ListBox mPartial;

  public TakesPartial(ListBox inListBox)
  {
    mPartial = inListBox;
  }

  @Override
  public void setValue(Double inValue)
  {
    switch (String.valueOf(inValue))
    {
      case "0.25":
        mPartial.setSelectedIndex(1);
        break;
      case "0.5":
        mPartial.setSelectedIndex(2);
        break;
      case "0.75":
        mPartial.setSelectedIndex(3);
        break;
      default:
        mPartial.setSelectedIndex(0);
        break;
    }
  }

  @Override
  public Double getValue()
  {
    Double ret = null;
    switch (mPartial.getSelectedIndex())
    {
      case 1:
        ret = 0.25;
        break;
      case 2:
        ret = 0.50;
        break;
      case 3:
        ret = 0.75;
        break;
      default:
        break;
    }
    return ret;
  }
}