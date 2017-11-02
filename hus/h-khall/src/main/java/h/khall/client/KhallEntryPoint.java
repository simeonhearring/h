package h.khall.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import h.khall.client.ui.LoginView;

public class KhallEntryPoint implements EntryPoint
{
  @Override
  public void onModuleLoad()
  {
    new LoginView(RootPanel.get());
  }
}