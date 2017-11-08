package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.MidweekPresenter;

public class MidweekView extends AbstractView<MidweekPresenter> implements MidweekPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, MidweekView>
  {
  }

  public MidweekView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new MidweekPresenter(this);
  }
}