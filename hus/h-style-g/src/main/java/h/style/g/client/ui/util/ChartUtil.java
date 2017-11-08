package h.style.g.client.ui.util;

import java.util.List;
import java.util.Map.Entry;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import h.style.g.shared.chart.Chart;

public class ChartUtil
{
  public static void chart(String inCanvasId, Chart inChart)
  {
    chartNative(inCanvasId, toJson(inChart).getJavaScriptObject());
  }

  private static JSONObject toJson(Chart inChart)
  {
    JSONObject ret = new JSONObject();
    ret.put("type", new JSONString(inChart.getCode()));
    ret.put("data", toJson(inChart.getData()));
    ret.put("options", toJson(inChart.getOptions()));
    return ret;
  }

  private static JSONObject toJson(Chart.Data inData)
  {
    JSONObject ret = new JSONObject();
    ret.put("labels", toJson(inData.getLabels()));
    ret.put("datasets", toJson(inData.getDatasets()));
    return ret;
  }

  private static JSONValue toJson(Chart.Datasets inDatasets)
  {
    JSONArray ret = new JSONArray();
    for (int i = 0; i < inDatasets.size(); i++)
    {
      ret.set(i, toJson(inDatasets.get(i)));
    }
    return ret;
  }

  private static JSONArray toJson(Chart.Labels inLabels)
  {
    JSONArray ret = new JSONArray();
    for (int i = 0; i < inLabels.size(); i++)
    {
      jsonString(ret, i, inLabels.get(i));
    }
    return ret;
  }

  private static JSONObject toJson(Chart.Options inOptions)
  {
    JSONObject ret = new JSONObject();

    Boolean responsive = inOptions.getResponsive();
    if (responsive != null)
    {
      ret.put("responsive", JSONBoolean.getInstance(responsive));
    }

    return ret;
  }

  private static JSONObject toJson(Chart.Dataset inDataset)
  {
    JSONObject ret = new JSONObject();

    for (Entry<String, String> value : inDataset.data().entrySet())
    {
      jsonString(ret, value.getKey(), value.getValue());
    }

    JSONArray array = new JSONArray();

    List<Double> data = inDataset.getData();
    for (int i = 0; i < data.size(); i++)
    {
      jsonNumber(array, i, data.get(i));
    }
    ret.put("data", array);
    return ret;
  }

  private static void jsonString(JSONObject inObject, String inKey, String inValue)
  {
    if (inValue != null)
    {
      inObject.put(inKey, new JSONString(inValue));
    }
  }

  private static void jsonString(JSONArray inObject, int inKey, String inValue)
  {
    if (inValue != null)
    {
      inObject.set(inKey, new JSONString(inValue));
    }
  }

  private static void jsonNumber(JSONArray inObject, int inKey, Double inValue)
  {
    if (inValue != null)
    {
      inObject.set(inKey, new JSONNumber(inValue));
    }
  }

  private static native void chartNative(String inCanvasId, JavaScriptObject inObj)
  /*-{
        var ctx = $doc.getElementById(inCanvasId).getContext("2d");
        new $wnd.Chart(ctx, inObj);
  }-*/;

  public static String data()
  {
    StringBuilder sb = new StringBuilder();

    sb.append("{");
    sb.append(
        "  \"labels\" : [\"January\", \"February\", \"March\", \"April\", \"May\", \"June\", \"July\"],");
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
}