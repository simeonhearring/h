package h.style.g.client.ui.common;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;

import h.model.shared.Debug;
import h.model.shared.Notice;
import h.model.shared.Params;
import h.model.shared.SessionInfo;
import h.style.g.client.service.bus.EventBus;
import h.style.g.client.service.rpc.RpcServiceAsync;
import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.util.JsniUtil;
import h.style.g.shared.command.LoggerCommand;
import h.style.g.shared.command.LoggerCommand.Level;
import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public class Global implements HasFire
{
  private static final Global INSTANCE = new Global();

  private EventBus mEventBus;

  private RpcServiceAsync mRpcService;

  private Debug mDebug = new DebugNotify(); // TODO remove

  private SessionInfo mInfo;

  public static void setEventBus(EventBus inEventBus)
  {
    INSTANCE.mEventBus = inEventBus;
  }

  public static void setRpcService(RpcServiceAsync inRpcServiceAsync)
  {
    INSTANCE.mRpcService = inRpcServiceAsync;
  }

  public static void setInfo(SessionInfo inInfo)
  {
    INSTANCE.mInfo = inInfo;
  }

  public static List<Notice> getNotices()
  {
    return INSTANCE.mInfo != null ? INSTANCE.mInfo.getNotices() : null;
  }

  public static void setDebug(Debug inDebug)
  {
    INSTANCE.mDebug = inDebug;
  }

  public static Global getInstance()
  {
    return INSTANCE;
  }

  // fire rpc & event

  @Override
  public void fire(Event<?>... inEvent)
  {
    fireS(inEvent);
  }

  public static void fireS(Event<?>... inEvent)
  {
    for (Event<?> value : inEvent)
    {
      debugFire(value);
      INSTANCE.mEventBus.fire(value);
    }
  }

  @Override
  public <C extends RpcCommand, R extends RpcResponse> void fire(C inCommand,
      AsyncCallback<R> inCallback)
  {
    if (inCommand != null)
    {
      inCommand.setUserInfo(userInfo());
      fireS(inCommand, inCallback);
    }
  }

  public static <C extends RpcCommand, R extends RpcResponse> void fireS(C inCommand,
      AsyncCallback<R> inCallback)
  {
    debugFire(inCommand.getClass());
    INSTANCE.mRpcService.fire(inCommand, inCallback);
  }

  @Override
  public <C extends RpcCommand> void fire(C inCommand)
  {
    fire(inCommand, new RpcCallback<RpcResponse>()
    {
      @Override
      public void onRpcSuccess(RpcResponse inResult)
      {
        // do nothing
      }
    });
  }

  @Override
  public <C extends RpcCommand> void fire(C inCommand, final Event<?>... inEvents)
  {
    fire(inCommand, new RpcCallback<RpcResponse>()
    {
      @Override
      public void onRpcSuccess(RpcResponse inResult)
      {
        for (Event<?> value : inEvents)
        {
          fire(value);
        }
      }
    });
  }

  // logging

  public static void logError(String inMessage, Throwable inCaught)
  {
    log(Level.ERROR, inMessage, inCaught);
  }

  public static void log(Level inLevel, String inMessage)
  {
    log(inLevel, inMessage, null);
  }

  public static void log(Level inLevel, String inMessage, Throwable inCaught)
  {
    LoggerCommand command = new LoggerCommand(inLevel, inMessage, inCaught);
    INSTANCE.fire(command, command);
  }

  // debug

  public static void debug(String inAction, String inMessage)
  {
    if (isDebugOn())
    {
      debug(inAction + "|" + inMessage);
    }
  }

  public static void debugClick(String inMessage)
  {
    if (isDebugOn())
    {
      debug("clicked|" + inMessage);
    }
  }

  public static void debugError(String inMessage)
  {
    if (isDebugOn())
    {
      debug("error|" + inMessage);
    }
  }

  public static void debugFire(Class<?> inClass)
  {
    if (isDebugOn())
    {
      debug("fire|" + inClass.getSimpleName());
    }
  }

  public static void debugFire(String inValue)
  {
    if (isDebugOn())
    {
      debug("fire|" + inValue);
    }
  }

  public static void debugBack(Class<?> inClass)
  {
    if (isDebugOn())
    {
      debug("back|" + inClass.getSimpleName());
    }
  }

  public static void debugBack(String inString)
  {
    if (isDebugOn())
    {
      debug("back|" + inString);
    }
  }

  public static void debugFire(Event<?> inClass)
  {
    if (isDebugOn())
    {
      debug("fire|" + inClass.toDebugString());
    }
  }

  public static void debugBack(Event<?> inEvent)
  {
    if (isDebugOn())
    {
      debug("back|" + inEvent.toDebugString());
    }
  }

  public static void debugAdd(Object inObject)
  {
    if (isDebugOn())
    {
      debug("add|" + inObject.toString());
    }
  }

  private static void debug(String inString)
  {
    INSTANCE.mDebug.debug(inString);
  }

  public static SessionInfo info()
  {
    return INSTANCE.mInfo;
  }

  public static String getEnvironment()
  {
    return INSTANCE.mInfo != null ? INSTANCE.mInfo.getEnvironment() : null;
  }

  public static boolean isDebug()
  {
    return info().contains(Params.debug.name(), Params.Debug.names());
  }

  public static String debugType()
  {
    return info().get(Params.debug.name());
  }

  @Override
  public <H extends EventHandler> HandlerRegistration addHandler(Type<H> inType, H inHandler)
  {
    debugAdd(inType);
    return INSTANCE.mEventBus.addHandler(inType, inHandler);
  }

  private static String userInfo()
  {
    String ret = null;
    if (info() != null)
    {
      ret = info().getUserInfo();
    }
    return ret;
  }

  public static Debug debug()
  {
    return INSTANCE.mDebug;
  }

  public static boolean isDebugOn()
  {
    return debug() != null;
  }

  public static void exportNativeDebug()
  {
    exportDebugNative();
  }

  private static native void exportDebugNative()
  /*-{
		$wnd.debug = $entry(function(inAction, inMessage) {
			@h.style.g.client.ui.common.Global::debug(Ljava/lang/String;Ljava/lang/String;)(inAction, inMessage);
		});

		$wnd.isDebugOn = $entry(function() {
			return @h.style.g.client.ui.common.Global::isDebugOn()();
		});
  }-*/;

  public static String getBrowser()
  {
    return JsniUtil.getUserAgent() + " / " + JsniUtil.getCompatMode() + " / "
        + JsniUtil.getDocumentMode();
  }

  public static boolean isSupportedBrowser()
  {
    return JsniUtil.isChrome() || JsniUtil.isIE9() || JsniUtil.isIE10() || JsniUtil.isIE11()
        || JsniUtil.isFirefox() || JsniUtil.isiPad() || JsniUtil.isiPhone() || JsniUtil.isSafari();
  }
}