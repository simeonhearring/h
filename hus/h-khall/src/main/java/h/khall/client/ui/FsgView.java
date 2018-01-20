package h.khall.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtbootstrap3.client.ui.ListBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.FsgPresenter;

public class FsgView extends AbstractView<FsgPresenter> implements FsgPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, FsgView>
  {
  }

  @UiField
  ListBox mBox;

  Map<Integer, Integer> mOptions;

  public FsgView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mOptions = new HashMap<>();
    mPresenter = new FsgPresenter(this).handlers();
  }

  @UiHandler(
  {
      "mBox"
  })
  public void onChange(ChangeEvent inEvent)
  {
    mPresenter.change(mBox.getSelectedValue());
  }

  @Override
  public void clear()
  {
    mBox.clear();
  }

  @Override
  public void add(String inItem, Integer inValue)
  {
    int pos = mBox.getItemCount();
    mBox.addItem(inItem, String.valueOf(inValue));
    mOptions.put(inValue, pos);
  }

  @Override
  public void set(Integer inValue)
  {
    Integer index = mOptions.get(inValue);
    mBox.setSelectedIndex(index);
  }
}