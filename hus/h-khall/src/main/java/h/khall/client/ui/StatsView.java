package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.html.Div;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
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

  @UiField
  Div mDiv;

  @UiField
  Heading mHead, mSubHead;

  @UiField
  Span mTopRight, mTime;

  @UiField
  HTML mFooter;

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

  @Override
  public void setHead(String inText)
  {
    mHead.setText(inText);
  }

  @Override
  public void setSubHead(String inText)
  {
    mSubHead.setText(inText);
  }

  @Override
  public void setTopRight(String inText)
  {
    mTopRight.setHTML(inText);
  }

  @Override
  public void setFooter(String inText)
  {
    mFooter.setHTML(inText);
  }

  @Override
  public void setTime(String inText)
  {
    mTime.setText(inText);
  }

  @Override
  public void setDataType(String inDataType)
  {
    mPresenter.setDataType(inDataType);
  }
}