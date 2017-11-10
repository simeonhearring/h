package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.ChartPresenter;
import h.style.g.client.ui.util.ChartUtil;
import h.style.g.shared.chart.Chart;

public class ChartView extends AbstractView<ChartPresenter> implements ChartPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ChartView>
  {
  }

  @UiField
  CanvasElement mCanvas;

  public ChartView()
  {
    mPresenter = new ChartPresenter().handlers();
    initWidget(BINDER.createAndBindUi(this));
    setElementId(mCanvas);
    mPresenter.initDisplay(this);
  }

  @Override
  protected void onUnload()
  {
    mPresenter.removeHandlers();
    mPresenter.initDisplay(null);
    mPresenter = null;
  }

  @Override
  public void load(Chart inChart)
  {
    ChartUtil.chart(mCanvas.getId(), inChart);
  }

  public void setDataType(String inDataType)
  {
    mPresenter.setDataType(inDataType);
  }
}