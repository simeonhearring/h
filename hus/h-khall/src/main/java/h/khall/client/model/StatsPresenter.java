package h.khall.client.model;

public class StatsPresenter extends AbstractPresenter<StatsPresenter.Display>
{
  public StatsPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}