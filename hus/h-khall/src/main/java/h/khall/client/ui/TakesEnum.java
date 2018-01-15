package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.ListBox;

import com.google.gwt.user.client.TakesValue;

import h.model.shared.util.EnumUtil;

public class TakesEnum<V extends Enum<?>> extends ListBox implements TakesValue<V>
{
  private V[] mEnums;

  @SuppressWarnings("unchecked")
  public void setEnums(V... inEnums)
  {
    mEnums = inEnums;
  }

  @Override
  public void setValue(V inValue)
  {
    int selected = 0;
    if (inValue != null)
    {
      String name = inValue.name();
      for (int i = 0; i < getItemCount(); i++)
      {
        if (name.equals(getValue(i)))
        {
          selected = i;
          break;
        }
      }
    }
    setSelectedIndex(selected);
  }

  @Override
  public V getValue()
  {
    return EnumUtil.valueOf(getSelectedValue(), mEnums);
  }
}