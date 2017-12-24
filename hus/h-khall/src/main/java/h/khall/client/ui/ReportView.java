package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.ReportPresenter;

public class ReportView extends AbstractView<ReportPresenter> implements ReportPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ReportView>
  {
  }

  public ReportView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new ReportPresenter(this);
  }
}