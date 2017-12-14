package h.khall.client.ui.event;

import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class ParticipantInfoEvent extends Event<ParticipantInfoEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(ParticipantInfoEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(ParticipantInfoEvent inEvent);
  }

  private Long mParticipantId;

  public ParticipantInfoEvent(Long inParticipantId)
  {
    mParticipantId = inParticipantId;
  }

  @Override
  public Type<Handler> getAssociatedType()
  {
    return TYPE;
  }

  @Override
  protected void dispatch(Handler inHandler)
  {
    debug(inHandler);
    inHandler.dispatch(this);
  }

  public Long getParticipantId()
  {
    return mParticipantId;
  }
}