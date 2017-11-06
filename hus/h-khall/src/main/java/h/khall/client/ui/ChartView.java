package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
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
    chart(parseJson(data()));
    // chart(json().getJavaScriptObject());
  }

  public static String data()
  {
    StringBuilder sb = new StringBuilder();

    sb.append("{");
    sb.append("  \"labels\" : [\"January\", \"February\", \"March\", \"April\", \"May\", \"June\", \"July\"],");
    sb.append("  \"datasets\" : [");
    sb.append("      {");
    sb.append("          \"label\" : \"Example dataset\",");
    sb.append("          \"backgroundColor\" : \"rgba(26,179,148,0.5)\",");
    sb.append("          \"borderColor\" : \"rgba(26,179,148,0.7)\",");
    sb.append("          \"pointBackgroundColor\" : \"rgba(26,179,148,1)\",");
    sb.append("          \"pointBorderColor\" : \"#fff\",");
    sb.append("          \"data\" : [ 28, 48, 40, 19, 86, 27, 90 ]");
    sb.append("      },");
    sb.append("      {");
    sb.append("          \"label\" : \"Example dataset\",");
    sb.append("          \"backgroundColor\" : \"rgba(220,220,220,0.5)\",");
    sb.append("          \"borderColor\" : \"rgba(220,220,220,1)\",");
    sb.append("          \"pointBackgroundColor\" : \"rgba(220,220,220,1)\",");
    sb.append("          \"pointBorderColor\" : \"#fff\",");
    sb.append("          \"data\" : [ 65, 59, 80, 81, 56, 55, 40 ]");
    sb.append("      }");
    sb.append("  ]");
    sb.append("}");

    return sb.toString();
  }

  public static <T extends JavaScriptObject> T parseJson(String inText)
  {
    return JsonUtils.safeEval(inText);
  }

  private static native void chart(JavaScriptObject inObj)
  /*-{
		$wnd.$(document).ready(function() {

			var lineOptions = {
				responsive : true
			};

			var ctx = $doc.getElementById("lineChart").getContext("2d");
			new $wnd.Chart(ctx, {
				type : 'line',
				data : inObj,
				options : lineOptions
			});
		});
  }-*/;
}