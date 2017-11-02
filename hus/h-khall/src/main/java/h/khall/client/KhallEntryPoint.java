package h.khall.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import h.khall.client.ui.LoginView;

public class KhallEntryPoint implements EntryPoint
{
  @Override
  public void onModuleLoad()
  {
    RootPanel.get().add(new LoginView());
    RootPanel.get().addStyleName("gray-bg");
  }
}