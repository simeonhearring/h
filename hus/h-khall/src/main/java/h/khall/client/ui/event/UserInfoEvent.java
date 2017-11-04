package h.khall.client.ui.event;

import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class UserInfoEvent extends Event<UserInfoEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(UserInfoEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(UserInfoEvent inEvent);
  }

  private String mName, mTitle;

  public UserInfoEvent(String inUserName, String inUserTitle)
  {
    mName = inUserName;
    mTitle = inUserTitle;
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

  public String getName()
  {
    return mName;
  }

  public String getTitle()
  {
    return mTitle;
  }
}