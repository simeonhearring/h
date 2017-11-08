package h.style.g.client.ui.common;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import h.model.shared.Debug;
import h.style.g.client.ui.util.JsniUtil;

public class Debugger implements Debug
{
  public enum Type
  {
    CONSOLE,
    NOTIFY;
  }

  private Type mType;

  public Debugger()
  {
  }

  public Debugger(Type inType)
  {
    setType(inType);
  }

  @Override
  public void debug(String inMessage)
  {
    if (mType != null)
    {
      switch (mType)
      {
        case CONSOLE:
          JsniUtil.console(inMessage);
          break;
        case NOTIFY:
          Notify.notify(inMessage);
          break;
        default:
          break;
      }
    }
  }

  public void setType(Type inType)
  {
    mType = inType;
  }

  @Override
  public void setType(String inType)
  {
    if (inType.equals(Type.CONSOLE.name()))
    {
      setType(Type.CONSOLE);
    }
    else if (inType.equals(Type.NOTIFY.name()))
    {
      setType(Type.NOTIFY);
    }
  }
}