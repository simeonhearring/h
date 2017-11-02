package h.style.g.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

import h.style.g.client.model.WindowDisplay;

public class WindowView extends JavaScriptObject implements WindowDisplay
{
  protected WindowView()
  {
  }

  public static native WindowView open(String inTarget, String inOptions)
  /*-{
		var ret = $wnd.open("", inTarget, inOptions);
		ret.focus(inTarget);
		return ret;
  }-*/;

  @Override
  public final native void close()
  /*-{
		this.close();
  }-*/;

  @Override
  public final native void setUrl(String inUrl)
  /*-{
		if (this.location) {
			this.location = inUrl;
		}
  }-*/;
}