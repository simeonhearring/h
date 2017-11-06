package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.ChartPresenter;
import h.style.g.client.ui.AbstractView;

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
    chart();
  }

  private static native void chart()
  /*-{
		$wnd.$(document).ready(function() {

			var lineData = {
				labels : [ "January", "February", "March", "April", "May", "June", "July" ],
				datasets : [
					{
						label : "Example dataset",
						backgroundColor : "rgba(26,179,148,0.5)",
						borderColor : "rgba(26,179,148,0.7)",
						pointBackgroundColor : "rgba(26,179,148,1)",
						pointBorderColor : "#fff",
						data : [ 28, 48, 40, 19, 86, 27, 90 ]
					},
					{
						label : "Example dataset",
						backgroundColor : "rgba(220,220,220,0.5)",
						borderColor : "rgba(220,220,220,1)",
						pointBackgroundColor : "rgba(220,220,220,1)",
						pointBorderColor : "#fff",
						data : [ 65, 59, 80, 81, 56, 55, 40 ]
					}
				]
			};

			var lineOptions = {
				responsive : true
			};


			var ctx = $doc.getElementById("lineChart").getContext("2d");
			new $wnd.Chart(ctx, {
				type : 'line',
				data : lineData,
				options : lineOptions
			});

		});
  }-*/;
}