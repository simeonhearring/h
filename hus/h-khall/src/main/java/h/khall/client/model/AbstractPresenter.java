package h.khall.client.model;

import h.khall.client.ui.event.LogoutEvent;
import h.khall.client.ui.event.ProfileEvent;
import h.khall.shared.model.Profile;
import h.style.g.client.model.Display;

public class AbstractPresenter<D extends Display>
    extends h.style.g.client.model.AbstractPresenter<D> implements ProfileEvent.Handler
{
  protected Profile mProfile;

  public AbstractPresenter()
  {
    addHandler(ProfileEvent.TYPE, this);
  }

  @Override
  public void dispatch(ProfileEvent inEvent)
  {
    mProfile = (Profile) inEvent.getProfile();
  }

  public void logout()
  {
    fire(new LogoutEvent());
  }
}