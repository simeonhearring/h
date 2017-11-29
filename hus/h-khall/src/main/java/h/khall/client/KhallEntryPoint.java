package h.khall.client;

import h.khall.client.ui.MainView;
import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.AttachEvent.TypeA;
import h.khall.client.ui.event.ProfileEvent;
import h.model.shared.SessionInfo;
import h.style.g.client.AbstractEntryPoint;
import h.style.g.client.model.CallBack;
import h.style.g.client.ui.common.Global;
import h.style.g.client.ui.event.LoadMainEvent;

public class KhallEntryPoint extends AbstractEntryPoint
  implements CallBack<SessionInfo>, ProfileEvent.Handler
{
  @Override
  public void dispatch(LoadMainEvent inEvent)
  {
    Global.getInstance().addHandler(ProfileEvent.TYPE, this);
    new MainView();
    sessionInfo(this);
  }

  @Override
  public void onCallBack(SessionInfo inInfo)
  {
    Global.setInfo(inInfo);
    fire(new AttachEvent(TypeA.LOGIN));
  }

  @Override
  public void dispatch(ProfileEvent inEvent)
  {
    setProfile(inEvent.getProfile());
  }

  @Override
  public String getEncrypt()
  {
    return "&pEncrypt=" + Global.info().get("congEnc");
  }
}