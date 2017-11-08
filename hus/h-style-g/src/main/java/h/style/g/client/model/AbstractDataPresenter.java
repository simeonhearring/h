package h.style.g.client.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.Event.Type;
import com.google.web.bindery.event.shared.HandlerRegistration;

import h.style.g.client.ui.common.Global;
import h.style.g.client.ui.common.HasFire;
import h.style.g.client.ui.common.RpcCallback;
import h.style.g.client.ui.event.Event;
import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public abstract class AbstractDataPresenter<D extends Display, T extends RpcResponse>
extends RpcCallback<T> implements Presenter<D>, HasFire
{
  protected D mDisplay;

  private HasFire mFire;

  private List<HandlerRegistration> mRegistrations;

  public AbstractDataPresenter()
  {
    setFire(Global.getInstance());
    mRegistrations = new ArrayList<>();
  }

  public void setFire(HasFire inFire)
  {
    mFire = inFire;
  }

  @Override
  public void go(D inDisplay)
  {
    initDisplay(inDisplay);
    loadData();
  }

  @Override
  public void initDisplay(D inDisplay)
  {
    mDisplay = inDisplay;
  }

  public final void loadData()
  {
    fire(getDataCommand(), this);
  }

  public D getDisplay()
  {
    return mDisplay;
  }

  public String format(String inPattern, Date inDate)
  {
    return mDisplay.format(inPattern, inDate);
  }

  public abstract RpcCommand getDataCommand();

  @Override
  public <H> HandlerRegistration addHandler(Type<H> inType, H inHandler)
  {
    return mFire.addHandler(inType, inHandler);
  }

  @Override
  public void fire(Event<?>... inEvent)
  {
    mFire.fire(inEvent);
  }

  @Override
  public void fire(RpcCommand inCommand)
  {
    mFire.fire(inCommand);
  }

  @Override
  public <C extends RpcCommand, R extends RpcResponse> void fire(C inCommand,
      AsyncCallback<R> inCallback)
  {
    mFire.fire(inCommand, inCallback);
  }

  @Override
  public <C extends RpcCommand> void fire(C inCommand, Event<?>... inEvents)
  {
    mFire.fire(inCommand, inEvents);
  }

  public void register(HandlerRegistration inHr)
  {
    mRegistrations.add(inHr);
  }

  public void removeHandlers()
  {
    for (HandlerRegistration value : mRegistrations)
    {
      value.removeHandler();
    }
    mRegistrations.clear();
  }
}