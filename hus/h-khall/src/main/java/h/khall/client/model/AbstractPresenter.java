package h.khall.client.model;

import h.khall.client.ui.event.ClientEvent;
import h.khall.client.ui.event.ProfileEvent;
import h.khall.shared.model.Client;
import h.khall.shared.model.Profile;
import h.style.g.client.model.Display;

public class AbstractPresenter<D extends Display> extends
  h.style.g.client.model.AbstractPresenter<D> implements ProfileEvent.Handler, ClientEvent.Handler
{
  protected Profile mProfile;
  protected Client mClient;

  public AbstractPresenter()
  {
    register(addHandler(ProfileEvent.TYPE, this));
    register(addHandler(ClientEvent.TYPE, this));
  }

  @Override
  public void dispatch(ProfileEvent inEvent)
  {
    mProfile = (Profile) inEvent.getProfile();
  }

  @Override
  public void dispatch(ClientEvent inEvent)
  {
    mClient = (Client) inEvent.getClient();
  }
}