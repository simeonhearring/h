package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.SamplePresenter;

public class SampleView extends AbstractView<SamplePresenter> implements SamplePresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, SampleView>
  {
  }

  public SampleView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new SamplePresenter(this);
  }
}