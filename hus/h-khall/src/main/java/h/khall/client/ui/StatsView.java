package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.StatsPresenter;

public class StatsView extends AbstractView<StatsPresenter> implements StatsPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, StatsView>
  {
  }

  public StatsView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new StatsPresenter(this);
  }
}