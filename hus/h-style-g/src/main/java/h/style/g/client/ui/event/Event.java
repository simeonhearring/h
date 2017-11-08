package h.style.g.client.ui.event;

import h.style.g.client.ui.common.Global;

public abstract class Event<H> extends com.google.web.bindery.event.shared.Event<H>
{
  public void debug(H inHandler)
  {
    if (isDebugOn())
    {
      Global.debugDispatch(inHandler.getClass().getSimpleName());
    }
  }

  public boolean isDebugOn()
  {
    return Global.isDebugOn();
  }

  @Override
  public String toDebugString()
  {
    return this.getClass().getSimpleName();
  }
}