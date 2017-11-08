package h.khall.client.model;

public class MidweekPresenter extends AbstractPresenter<MidweekPresenter.Display>
{
  public MidweekPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}