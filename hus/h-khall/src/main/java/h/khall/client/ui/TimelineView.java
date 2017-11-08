package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.TimelinePresenter;

public class TimelineView extends AbstractView<TimelinePresenter>
  implements TimelinePresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, TimelineView>
  {
  }

  public TimelineView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new TimelinePresenter(this);
  }
}