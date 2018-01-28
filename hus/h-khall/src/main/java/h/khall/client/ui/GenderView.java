package h.khall.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtbootstrap3.client.ui.ListBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.Event;

import h.khall.client.model.GenderPresenter;

public class GenderView extends AbstractView<GenderPresenter> implements GenderPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, GenderView>
  {
  }

  @UiField
  ListBox mBox;

  Map<String, Integer> mOptions;

  public GenderView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mOptions = new HashMap<>();
    mPresenter = new GenderPresenter(this).handlers();
  }

  @Override
  public boolean isSource(Event<?> inEvent)
  {
    return mBox.equals(inEvent.getSource());
  }

  @Override
  public HandlerRegistration addChangeHandler(ChangeHandler inHandler)
  {
    return mBox.addChangeHandler(inHandler);
  }

  @Override
  public String getSelectedValue()
  {
    return mBox.getSelectedValue();
  }

  @Override
  public void clear()
  {
    mBox.clear();
  }

  @Override
  public void editGender(boolean inEnable)
  {
    mBox.setEnabled(inEnable);
  }

  @Override
  public void add(String inItem, String inValue)
  {
    int pos = mBox.getItemCount();
    mBox.addItem(inItem, inValue);
    mOptions.put(inValue, pos);
  }

  @Override
  public void set(String inValue)
  {
    Integer index = mOptions.get(inValue);
    mBox.setSelectedIndex(index);
  }
}