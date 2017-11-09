package h.style.g.client;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

import h.model.shared.SessionInfo;
import h.style.g.client.model.CallBack;
import h.style.g.client.service.rpc.RpcService;
import h.style.g.client.service.rpc.RpcServiceAsync;
import h.style.g.client.ui.common.Global;
import h.style.g.client.ui.common.RpcCallback;
import h.style.g.client.ui.event.AlertEvent;
import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.LoadMainEvent;
import h.style.g.client.ui.service.bus.GwtEventBus;
import h.style.g.client.ui.util.JsniUtil;
import h.style.g.shared.command.LoggerCommand.Level;
import h.style.g.shared.command.SessionInfoCommand;

public abstract class AbstractEntryPoint
    implements EntryPoint, AlertEvent.Handler, LoadMainEvent.Handler
{
  @Override
  public void onModuleLoad()
  {
    GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler()
    {
      @Override
      public void onUncaughtException(Throwable inE)
      {
        Window.alert(
            "Opps!  We did not process your request correctly.  Please report this by using 'Contact Us'.  (How embarrassing!)");
        Global.logError("UNCAUGHT EXCEPTION", inE);
      }
    });
    Global.setRpcService((RpcServiceAsync) GWT.create(RpcService.class));
    Global.setEventBus(new GwtEventBus());
    Global.getInstance().addHandler(AlertEvent.TYPE, this);
    Global.getInstance().addHandler(LoadMainEvent.TYPE, this);
    Global.exportNativeDebug();
    Global.log(Level.INFO, "Started... " + JsniUtil.getBrowserInfo(), null);
    fire(new LoadMainEvent());
  }

  public void fire(Event<?>... inEvent)
  {
    Global.fireS(inEvent);
  }

  @Override
  public void dispatch(AlertEvent inEvent)
  {
    Notify.notify(inEvent.getMessage());
  }

  public void disableBrowserBack()
  {
    History.newItem("x");
    History.addValueChangeHandler(new ValueChangeHandler<String>()
    {
      @Override
      public void onValueChange(ValueChangeEvent<String> event)
      {
        String historyToken = event.getValue();
        if (!historyToken.equals("X"))
        {
          History.newItem("X");
        }
      }
    });
  }

  public void sessionInfo(final CallBack<SessionInfo> inCallback)
  {
    Global.fireS(new SessionInfoCommand(), new RpcCallback<SessionInfoCommand>()
    {
      @Override
      public void onRpcSuccess(SessionInfoCommand inResult)
      {
        inCallback.onCallBack(inResult.getData());
      }
    });
  }
}