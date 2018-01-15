package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.ListBox;

import com.google.gwt.user.client.TakesValue;

public class TakesPartial extends ListBox implements TakesValue<Double>
{
  public TakesPartial()
  {
    addItem("-Select-");
    addItem("15 min.");
    addItem("30 min.");
    addItem("45 min.");
  }

  @Override
  public void setValue(Double inValue)
  {
    switch (String.valueOf(inValue))
    {
      case "0.25":
        setSelectedIndex(1);
        break;
      case "0.5":
        setSelectedIndex(2);
        break;
      case "0.75":
        setSelectedIndex(3);
        break;
      default:
        setSelectedIndex(0);
        break;
    }
  }

  @Override
  public Double getValue()
  {
    Double ret = null;
    switch (getSelectedIndex())
    {
      case 1:
        ret = 0.25;
        break;
      case 2:
        ret = 0.5;
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