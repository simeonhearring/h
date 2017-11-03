package h.khall.client;

import h.khall.client.ui.MainView;
import h.khall.client.ui.event.LoginEvent;
import h.style.g.client.AbstractEntryPoint;
import h.style.g.client.model.CallBack;
import h.style.g.client.ui.common.Global;
import h.style.g.client.ui.event.LoadMainEvent;

public class KhallEntryPoint extends AbstractEntryPoint implements CallBack<Void>
{
  @Override
  public void dispatch(LoadMainEvent inEvent)
  {
    new MainView();
    sessionInfo(this);
  }

  @Override
  public void onCallBack(Void inResult)
  {
    Global.fireS(new LoginEvent());
  }
}