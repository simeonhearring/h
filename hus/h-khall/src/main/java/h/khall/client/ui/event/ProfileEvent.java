package h.khall.client.ui.event;

import h.model.shared.Profile;
import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class ProfileEvent extends Event<ProfileEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(ProfileEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(ProfileEvent inEvent);
  }

  private Profile mProfile;

  public ProfileEvent(Profile inProfile)
  {
    mProfile = inProfile;
  }

  @Override
  public Type<Handler> getAssociatedType()
  {
    return TYPE;
  }

  @Override
  protected void dispatch(Handler inHandler)
  {
    inHandler.dispatch(this);
  }

  public Profile getProfile()
  {
    return mProfile;
  }
}