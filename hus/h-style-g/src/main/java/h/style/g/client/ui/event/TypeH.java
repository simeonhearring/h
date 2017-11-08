package h.style.g.client.ui.event;

import com.google.web.bindery.event.shared.Event.Type;

public class TypeH<H> extends Type<H>
{
  private String mDebugName;

  public TypeH(Class<?> inClass)
  {
    mDebugName = inClass.getSimpleName();
  }

  @Override
  public String toString()
  {
    return mDebugName;
  }
}