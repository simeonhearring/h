package h.khall.client.ui.event;

import h.model.shared.Client;
import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class ClientEvent extends Event<ClientEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(ClientEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(ClientEvent inEvent);
  }

  private Client mClient;

  public ClientEvent(Client inClient)
  {
    mClient = inClient;
  }

  @Override
  public Type<Handler> getAssociatedType()
  {
    return TYPE;
  }

  @Override
  protected void dispatch(Handler inHandler)
  {
    debug(inHandler);
    inHandler.dispatch(this);
  }

  public Client getClient()
  {
    return mClient;
  }
}