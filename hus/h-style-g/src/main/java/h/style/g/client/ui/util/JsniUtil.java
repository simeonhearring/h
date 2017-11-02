package h.style.g.client.ui.util;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;

public final class JsniUtil
{
  private JsniUtil()
  {
  }

  public static boolean isDndIE()
  {
    return isIEBrowser();
  }

  public static String getBrowserInfo()
  {
    StringBuilder sb = new StringBuilder("\n[");
    sb.append(JsniUtil.getUserAgent()).append("\n");
    sb.append(JsniUtil.getCompatMode()).append("\n");
    sb.append(JsniUtil.getDocumentMode()).append("]");
    return sb.toString();
  }

  public static void unescape(Map<String, String> inMap)
  {
    if (GWT.isClient())
    {
      for (Map.Entry<String, String> entry : inMap.entrySet())
      {
        entry.setValue(unescapeNative(entry.getValue()));
      }
    }
  }

  public static void launchFrame(String inLink, String inFrame)
  {
    if (GWT.isClient())
    {
      launchFrameNative(inLink, inFrame);
    }
  }

  public static void launchFrame(String inUrl, String inFrame, String inParams)
  {
    if (GWT.isClient())
    {
      launchFrameNative(inUrl, inFrame, inParams);
    }
  }

  public static void toggleFrame(String inFrame, String inParams)
  {
    if (GWT.isClient())
    {
      toggleFrameNative(inFrame, inParams);
    }
  }

  public static void setWindowLocation(String inUrl)
  {
    if (GWT.isClient())
    {
      setWindowLocationNative(inUrl);
    }
  }

  public static void reload()
  {
    if (GWT.isClient())
    {
      reloadNative();
    }
  }

  public static String getUrl()
  {
    if (GWT.isClient())
    {
      return getUrlNative();
    }
    else
    {
      return null;
    }
  }

  public static String getStarpanelSessionId()
  {
    if (GWT.isClient())
    {
      return getStarpanelSessionIdNative();
    }
    else
    {
      return null;
    }
  }

  public static void mailTo(String inAddress, String inSubject, String inBody)
  {
    if (GWT.isClient())
    {
      mailToNative(inAddress, URL.encode(inSubject), URL.encode(inBody));
    }
  }

  public static void savePssEditorIfDirty(String inElementId)
  {
    if (GWT.isClient())
    {
      savePssEditorIfDirtyNative(inElementId);
    }
  }

  public static Boolean getPssEditorFrameDirty(String inElementId)
  {
    if (GWT.isClient())
    {
      return !"".equals(getPssEditorFrameDirtyNative(inElementId));
    }
    else
    {
      return false;
    }
  }

  public static String getUserAgent()
  {
    if (GWT.isClient())
    {
      return getUserAgentNative();
    }
    else
    {
      return "";
    }
  }

  public static void resizeToMax()
  {
    resizeTo(getScreenAvailWidthNative(), getScreenAvailHeightNative());
  }

  public static void resizeTo(int inWidth, int inHeight)
  {
    Window.resizeTo(inWidth, inHeight);
  }

  public static String getBrowserSize()
  {
    return Window.getClientWidth() + "x" + Window.getClientHeight();
  }

  public static String getScreenSize()
  {
    if (GWT.isClient())
    {
      return getScreenWidthNative() + "x" + getScreenHeightNative();
    }
    else
    {
      return null;
    }
  }

  public static String getAvailScreenSize()
  {
    if (GWT.isClient())
    {
      return getScreenAvailWidthNative() + "x" + getScreenAvailHeightNative();
    }
    else
    {
      return null;
    }
  }

  public static String getCompatMode()
  {
    if (GWT.isClient())
    {
      return getCompatModeNative();
    }
    else
    {
      return null;
    }
  }

  public static String getDocumentMode()
  {
    if (GWT.isClient())
    {
      return getDocumentModeNative();
    }
    else
    {
      return null;
    }
  }

  public static boolean isChrome()
  {
    return getUserAgent().contains("chrome");
  }

  public static boolean isFirefox()
  {
    return getUserAgent().toLowerCase().contains("firefox");
  }

  public static boolean isiPhone()
  {
    return getUserAgent().toLowerCase().contains("iphone");
  }

  public static boolean isiPad()
  {
    return getUserAgent().toLowerCase().contains("ipad");
  }

  public static boolean isSafari()
  {
    return getUserAgent().toLowerCase().contains("safari");
  }

  public static boolean isIEBrowser()
  {
    return getUserAgent().contains("msie") || isIE11() || isIEEdge();
  }

  public static boolean isIE7Or9()
  {
    return isIE7() || isIE9();
  }

  public static boolean isIE7()
  {
    return getUserAgent().contains("msie 7");
  }

  public static boolean isIE10()
  {
    return getUserAgent().contains("msie 10");
  }

  public static boolean isIE11()
  {
    return getUserAgent().contains("rv:11.0");
  }

  public static boolean isIEEdge()
  {
    return getUserAgent().contains("edge");
  }

  public static boolean isBrowser(String inValue)
  {
    return inValue != null && getUserAgent().contains(inValue);
  }

  public static boolean isIE9()
  {
    return getUserAgent().contains("msie 9");
  }

  public static void openFocusWindow(String inUrl, String inName, String inFeatures)
  {
    if (GWT.isClient())
    {
      openFocusWindowNative(inUrl, inName, inFeatures);
    }
  }

  public static void console(String inMessage)
  {
    if (GWT.isClient())
    {
      try
      {
        consoleNative(inMessage);
      }
      catch (Exception e)
      {
      }
    }
  }

  public static boolean isFramed()
  {
    boolean ret = false;
    if (GWT.isClient())
    {
      try
      {
        ret = isFramedNative();
      }
      catch (Exception e)
      {
      }
    }
    return ret;
  }

  public static boolean isDocumentMode8()
  {
    return "8".equals(String.valueOf(getDocumentMode()));
  }

  public static boolean isPlaceholderSupport()
  {
    if (GWT.isClient())
    {
      try
      {
        return isPlaceholderSupportNative();
      }
      catch (Exception e)
      {
      }
    }
    return false;
  }

  public static void consoleClear()
  {
    if (GWT.isClient())
    {
      try
      {
        consoleClearNative();
      }
      catch (Exception e)
      {
      }
    }
  }

  public static void callStarPanelAdvance()
  {
    if (GWT.isClient())
    {
      try
      {
        callStarPanelAdvanceNative();
      }
      catch (Exception e)
      {
      }
    }
  }

  public static void closeWindow()
  {
    if (GWT.isClient())
    {
      try
      {
        closeWindowNative();
      }
      catch (Exception e)
      {
        // do nothing
      }
    }
  }

  public static String getFrameId(String inDefault)
  {
    String ret = null;
    if (GWT.isClient())
    {
      try
      {
        ret = getFrameIdNative();
      }
      catch (Exception e)
      {
        ret = inDefault;
      }
    }
    return ret;
  }

  private static native String getFrameIdNative()
  /*-{
		var frm = $wnd.frameElement;
		return frm.id;
  }-*/;

  private static native void reloadNative()
  /*-{
		document.location.reload(true);
  }-*/;

  private static native String getUserAgentNative()
  /*-{
		return navigator.userAgent.toLowerCase();
  }-*/;

  public static native String stringify(JavaScriptObject inJso)
  /*-{
		return JSON.stringify(inJso);
  }-*/;

  public static native JavaScriptObject parse(String inString)
  /*-{
		return JSON.parse(inString);
  }-*/;

  private native Element getHead()
  /*-{
		return $doc.getElementsByTagName('head')[0];
  }-*/;

  private static native void mailToNative(String inAddress, String inSubject, String inBody)
  /*-{
		$wnd.location = "mailto:" + inAddress + "?subject=" + inSubject
				+ "&body=" + inBody;
  }-*/;

  private static native String unescapeNative(String inVal)
  /*-{
		return unescape(inVal);
  }-*/;

  private static native int getScreenHeightNative()
  /*-{
		return $wnd.window.screen.height;
  }-*/;

  private static native int getScreenWidthNative()
  /*-{
		return $wnd.window.screen.width;
  }-*/;

  private static native int getScreenAvailHeightNative()
  /*-{
		return $wnd.window.screen.availHeight;
  }-*/;

  private static native int getScreenAvailWidthNative()
  /*-{
		return $wnd.window.screen.availWidth;
  }-*/;

  private static native String getCompatModeNative()
  /*-{
		return $wnd.document.compatMode;
  }-*/;

  private static native String getDocumentModeNative()
  /*-{
		return $wnd.document.documentMode;
  }-*/;

  private static native String getUrlNative()
  /*-{
		return $wnd.location.href;
  }-*/;

  private static native void setWindowLocationNative(String inUrl)
  /*-{
		$wnd.location.href = inUrl;
  }-*/;

  private static native void launchFrameNative(String inUrl, String inFrame, String inParams)
  /*-{
		$wnd.open(inUrl, inFrame, inParams);
  }-*/;

  private static native void toggleFrameNative(String inFrame, String inParams)
  /*-{
		$wnd.top.sFN(inFrame, inParams, inParams);
  }-*/;

  private static native void launchFrameNative(String inLink, String inFrame)
  /*-{
		$wnd.top.LnkC(inLink, inFrame, $wnd);
  }-*/;

  private static native void savePssEditorIfDirtyNative(String inId)
  /*-{
		var pssFrame = $wnd.document.getElementById(inId);
		if (pssFrame.contentWindow.FrameDirty) {
			pssFrame.contentWindow.saveAllergies();
		}
  }-*/;

  private static native String getPssEditorFrameDirtyNative(String inId)
  /*-{
		var pssFrame = $wnd.document.getElementById(Id);
		return pssFrame.contentWindow.FrameDirty;
  }-*/;

  private static native String getStarpanelSessionIdNative()
  /*-{
		if (top.SPIP != null) {
			return top.SPIP
		} else {
			return null;
		}
  }-*/;

  private static native boolean isFramedNative()
  /*-{
		return $wnd.window.self !== $wnd.window.top;
  }-*/;

  private static native void closeWindowNative()
  /*-{
		$wnd.close();
  }-*/;

  private static native Element openWindowNative(String inUrl, String inName, String inFeatures)
  /*-{
		return $wnd.open(inUrl, inName, inFeatures);
  }-*/;

  private static native void openFocusWindowNative(String inUrl, String inName, String inFeatures)
  /*-{
		$wnd.open(inUrl, inName, inFeatures).focus(inName);
  }-*/;

  private static native boolean isWindowOpenNative(Element inWindow)
  /*-{
		return !!(inWindow && !inWindow.closed);
  }-*/;

  private static native void closeWindowNative(Element inWindow)
  /*-{
		inWindow.close();
  }-*/;

  private static native void closeOpenerWindowNative(Element inWindow)
  /*-{
		inWindow.opener.close();
  }-*/;

  private static native void focusWindowNative(Element inWindow)
  /*-{
		inWindow.focus();
  }-*/;

  private static native void callStarPanelAdvanceNative()
  /*-{
		$wnd.parent.top.ADVANCEnextTab($wnd);
  }-*/;

  private static native void consoleNative(String inMessage)
  /*-{
		console.log(inMessage);
  }-*/;

  private static native void consoleClearNative()
  /*-{
		console.clear();
  }-*/;

  private static native boolean isPlaceholderSupportNative()
  /*-{
		var i = document.createElement('input');
		return 'placeholder' in i;
  }-*/;
}