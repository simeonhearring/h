package h.style.g.client.ui.event;

import com.google.gwt.event.shared.GwtEvent;

public class TypeH<H> extends GwtEvent.Type<H>
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