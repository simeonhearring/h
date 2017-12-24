package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.IntegerBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.InputDisplay;

public class NumberView extends AbstractValueBox<Integer>
  implements InputDisplay<Integer>, HasValueChangeHandlers<Integer>
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, NumberView>
  {
  }

  @UiField
  IntegerBox mInput;

  public NumberView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setTextBox(mInput);
  }

  public void setTabindex(int inIndex)
  {
    mInput.setTabIndex(inIndex);
  }

  public void setClass(String inName)
  {
    mInput.setStyleName(inName);
  }

  public void setAddClass(String inName)
  {
    mInput.addStyleName(inName);
  }
}