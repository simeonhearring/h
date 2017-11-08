package h.style.g.client.ui.event;

import h.style.g.shared.chart.Chart;

public class ChartEvent extends Event<ChartEvent.Handler>
{
  public interface Handler extends EventHandler
  {
    void dispatch(ChartEvent inEvent);
  }

  public static final TypeH<Handler> TYPE = new TypeH<>(ChartEvent.class);

  private final String mCanvasId;
  private final Chart mChart;

  public ChartEvent(String inCanvasId, Chart inChart)
  {
    mCanvasId = inCanvasId;
    mChart = inChart;
  }

  @Override
  public Type<Handler> getAssociatedType()
  {
    return TYPE;
  }

  @Override
  protected void dispatch(Handler inHandler)
  {
    inHandler.dispatch(this);
  }

  public String getCanvasId()
  {
    return mCanvasId;
  }

  public Chart getChart()
  {
    return mChart;
  }

  @Override
  public String toString()
  {
    return ChartEvent.class.getSimpleName() + " " + mCanvasId;
  }
}
