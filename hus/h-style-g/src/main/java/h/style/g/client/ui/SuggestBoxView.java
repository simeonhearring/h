package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.SuggestBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class SuggestBoxView extends AbstractView
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, SuggestBoxView>
  {
  }

  @UiField
  SuggestBox mTextBox;

  public SuggestBoxView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  public boolean isEnabled()
  {
    return mTextBox.isEnabled();
  }

  public void setEnabled(boolean inEnabled)
  {
    mTextBox.setEnabled(inEnabled);
  }
}