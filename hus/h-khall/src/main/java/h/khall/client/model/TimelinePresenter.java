package h.khall.client.model;

public class TimelinePresenter extends AbstractPresenter<TimelinePresenter.Display>
{
  public TimelinePresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}