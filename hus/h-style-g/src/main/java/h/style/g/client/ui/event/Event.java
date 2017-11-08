package h.style.g.client.ui.event;

public abstract class Event<H> extends com.google.web.bindery.event.shared.Event<H>
{
  @Override
  public String toDebugString()
  {
    return this.getClass().getSimpleName();
  }
}