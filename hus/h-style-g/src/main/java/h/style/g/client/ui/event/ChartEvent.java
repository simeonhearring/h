package h.style.g.client.ui.event;

import h.style.g.shared.chart.Chart;

public class ChartEvent extends Event<ChartEvent.Handler>
{
  public interface Handler
  {
    void dispatch(ChartEvent inEvent);
  }

  public static final TypeH<Handler> TYPE = new TypeH<>(ChartEvent.class);

  private final Chart mChart;

  public ChartEvent(Chart inChart)
  {
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

  public Chart getChart()
  {
    return mChart;
  }
}
