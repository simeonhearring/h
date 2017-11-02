package h.style.g.client.ui;

import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.SelectDisplay;

public class SelectView extends AbstractView implements SelectDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, SelectView>
  {
  }

  @UiField
  Select mSelect;

  public SelectView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  @Override
  public void add(Option inOption)
  {
    mSelect.add(inOption);
  }

  @Override
  public String getValue()
  {
    return mSelect.getValue();
  }

  @Override
  public void setValue(String inValue)
  {
    mSelect.setValue(inValue);
  }
}