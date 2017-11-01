package h.khall.client;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import com.google.gwt.core.client.EntryPoint;

public class KhallEntryPoint implements EntryPoint
{
  @Override
  public void onModuleLoad()
  {
    Notify.notify("Hello");
  }
}