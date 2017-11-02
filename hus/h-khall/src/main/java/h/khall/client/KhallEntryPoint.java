package h.khall.client;

import com.google.gwt.user.client.ui.RootPanel;

import h.khall.client.ui.MainView;
import h.khall.client.ui.RegisterView;
import h.style.g.client.AbstractEntryPoint;
import h.style.g.client.ui.event.LoadMainEvent;

public class KhallEntryPoint extends AbstractEntryPoint
{
  @Override
  public void dispatch(LoadMainEvent inEvent)
  {
    new MainView();

    new RegisterView().attach(RootPanel.get());
    // new LoginView().attach(RootPanel.get());
    // Global.getInstance().fire(new LoginEvent());
  }
}