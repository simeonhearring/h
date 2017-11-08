package h.style.g.client.ui.common;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import h.model.shared.Debug;

public class DebugNotify implements Debug
{
  @Override
  public void debug(String inString)
  {
    Notify.notify(inString);
  }

  @Override
  public void setType(String inType)
  {
  }
}