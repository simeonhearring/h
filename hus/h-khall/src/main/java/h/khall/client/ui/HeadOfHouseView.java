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

import h.khall.client.model.HeadOfHousePresenter;

public class HeadOfHouseView extends AbstractView<HeadOfHousePresenter>
  implements HeadOfHousePresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, HeadOfHouseView>
  {
  }

  @UiField
  ListBox mBox;

  Map<Long, Integer> mOptions;

  public HeadOfHouseView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mOptions = new HashMap<>();
    mPresenter = new HeadOfHousePresenter(this).handlers();
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
  public Long getSelectedValue()
  {
    return Long.valueOf(mBox.getSelectedValue());
  }

  @Override
  public void clear()
  {
    mBox.clear();
  }

  @Override
  public void editHoh(boolean inEnable)
  {
    mBox.setEnabled(inEnable);
  }

  @Override
  public void add(String inItem, Long inValue)
  {
    int pos = mBox.getItemCount();
    mBox.addItem(inItem, String.valueOf(inValue));
    mOptions.put(inValue, pos);
  }

  @Override
  public void set(Long inValue)
  {
    Integer index = mOptions.get(inValue);
    if (index == null)
    {
      index = 0;
    }
    mBox.setSelectedIndex(index);
  }
}