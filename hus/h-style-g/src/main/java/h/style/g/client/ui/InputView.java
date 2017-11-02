package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.constants.InputType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.InputDisplay;

public class InputView extends AbstractValueBox implements InputDisplay<String>
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, InputView>
  {
  }

  @UiField
  Input mInput;

  public InputView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setTextBox(mInput);
  }

  public void setType(InputType inInputType)
  {
    mInput.setType(inInputType);
  }
}