package h.khall.shared.model;

import h.style.g.shared.chart.Chart;

@SuppressWarnings("serial")
public class Client extends h.model.shared.Client
{
  private Chart mChart;

  public Chart getChart()
  {
    return mChart;
  }

  public void setChart(Chart inChart)
  {
    mChart = inChart;
  }
}