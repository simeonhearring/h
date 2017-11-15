package h.khall.client.model;

public class ParticipantPresenter extends AbstractPresenter<ParticipantPresenter.Display>
{
  public ParticipantPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}