package h.style.g.client.ui.common;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.Event.Type;
import com.google.web.bindery.event.shared.HandlerRegistration;

import h.style.g.client.ui.event.Event;
import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public interface HasFire
{
  <H> HandlerRegistration addHandler(Type<H> inType, H inHandler);

  void fire(Event<?>... inEvent);

  <C extends RpcCommand> void fire(C inCommand);

  <C extends RpcCommand, R extends RpcResponse> void fire(C inCommand, AsyncCallback<R> inCallback);

  <C extends RpcCommand> void fire(C inCommand, Event<?>... inEvents);
}