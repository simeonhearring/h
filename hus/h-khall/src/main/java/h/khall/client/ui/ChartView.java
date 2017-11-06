package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.ChartPresenter;
import h.style.g.client.ui.AbstractView;
import h.style.g.client.ui.util.ChartUtil;
import h.style.g.shared.chart.Chart;

public class ChartView extends AbstractView implements ChartPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ChartView>
  {
  }

  ChartPresenter mPresenter;

  public ChartView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new ChartPresenter(this);
  }

  @Override
  public void attach(HasWidgets inPanel)
  {
    inPanel.clear();
    inPanel.add(this);
  }

  @Override
  protected void onLoad()
  {
    ChartUtil.chart("lineChart", Chart.sample());
  }
}