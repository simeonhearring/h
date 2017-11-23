package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.StatsPresenter;
import h.style.g.client.ui.util.ChartUtil;
import h.style.g.shared.chart.Chart;

public class StatsView extends AbstractView<StatsPresenter> implements StatsPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, StatsView>
  {
  }

  @UiField
  ChartView mChart;

  public StatsView()
  {
    mPresenter = new StatsPresenter().handlers();
    initWidget(BINDER.createAndBindUi(this));
    mPresenter.initDisplay(this);
  }

  @Override
  public void load(Chart inChart)
  {
    ChartUtil.chart(mChart.getCanvasId(), inChart);
  }

  public void setDataType(String inDataType)
  {
    mPresenter.setDataType(inDataType);
  }
}