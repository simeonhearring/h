package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.CheckBoxButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.InputDisplay;
import h.style.g.client.ui.NumberView;

public class HoursView extends h.style.g.client.ui.AbstractView
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, HoursView>
  {
  }

  @UiField
  NumberView mBox;

  @UiField
  CheckBoxButton mCheck;

  public HoursView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mBox.addStyleName("kh_box");
  }

  public void setPlaceholder(String inPlaceholder)
  {
    mBox.setPlaceholder(inPlaceholder);
  }

  public void setClass(String inStyleName)
  {
    mBox.setStyleName(inStyleName);
  }

  public void setTabindex(int inIndex)
  {
    mBox.setTabindex(inIndex);
  }

  public InputDisplay<Integer> getBox()
  {
    return mBox;
  }

  public TakesValue<Boolean> getCheck()
  {
    return mCheck;
  }

  public void addChangeHandler(ChangeHandler inHandler)
  {
    mBox.addChangeHandler(inHandler);
    mCheck.addChangeHandler(inHandler);
  }
}