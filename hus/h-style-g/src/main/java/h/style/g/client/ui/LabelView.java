package h.style.g.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.LabelDisplay;

public class LabelView extends AbstractLabel implements LabelDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, LabelView>
  {
  }

  @UiField
  HTML mTextBox;

  public LabelView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setLabel(mTextBox);
  }

  public LabelView(String inLabel)
  {
    this();
    setText(inLabel);
  }
}