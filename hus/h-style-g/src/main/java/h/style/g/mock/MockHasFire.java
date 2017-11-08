package h.style.g.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.Event.Type;
import com.google.web.bindery.event.shared.HandlerRegistration;

import h.style.g.client.ui.common.HasFire;
import h.style.g.client.ui.event.Event;
import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public class MockHasFire implements HasFire
{
  private Event<?>[] mEvent;
  private RpcCommand mCommand;
  private AsyncCallback<?> mCallback;
  private List<Type<?>> mTypes = new ArrayList<>();
  private Map<Type<?>, Object> mHandlers = new HashMap<>();

  @Override
  public <H> HandlerRegistration addHandler(Type<H> inType, H inHandler)
  {
    mTypes.add(inType);
    mHandlers.put(inType, inHandler);
    return null;
  }

  public List<Type<?>> getTypes()
  {
    return mTypes;
  }

  public Map<Type<?>, Object> getHandlers()
  {
    return mHandlers;
  }

  @Override
  public void fire(Event<?>... inEvent)
  {
    mEvent = inEvent;
  }

  @Override
  public <C extends RpcCommand> void fire(C inCommand)
  {
    mCommand = inCommand;
  }

  @Override
  public <C extends RpcCommand, R extends RpcResponse> void fire(C inCommand,
      AsyncCallback<R> inCallback)
  {
    mCommand = inCommand;
    mCallback = inCallback;
  }

  @Override
  public <C extends RpcCommand> void fire(C inCommand, Event<?>... inEvents)
  {
    mCommand = inCommand;
    mEvent = inEvents;
  }

  public Event<?>[] getEvent()
  {
    return mEvent;
  }

  public RpcCommand getCommand()
  {
    return mCommand;
  }

  public AsyncCallback<?> getCallback()
  {
    return mCallback;
  }
}