package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.ListBox;

import com.google.gwt.user.client.TakesValue;

import h.model.shared.util.EnumUtil;

public class TakesEnum<V extends Enum<?>> implements TakesValue<V>
{
  private ListBox mListBox;
  private V[] mEnums;

  public TakesEnum(ListBox inListBox)
  {
    mListBox = inListBox;
  }

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
      for (int i = 0; i < mListBox.getItemCount(); i++)
      {
        if (name.equals(mListBox.getValue(i)))
        {
          selected = i;
          break;
        }
      }
    }
    mListBox.setSelectedIndex(selected);
  }

  @Override
  public V getValue()
  {
    return EnumUtil.valueOf(mListBox.getSelectedValue(), mEnums);
  }
}