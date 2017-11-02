package h.style.g.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.HeaderBarDisplay;

public class HeaderBarView extends Composite implements HeaderBarDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, HeaderBarView>
  {
  }

  @UiField
  InlineLabel mText, mText1, mText2, mText3;

  public HeaderBarView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  @Override
  public HasText getText()
  {
    return mText;
  }

  @Override
  public HasText getText1()
  {
    return mText1;
  }

  @Override
  public HasText getText2()
  {
    return mText2;
  }

  @Override
  public HasText getText3()
  {
    return mText3;
  }
}