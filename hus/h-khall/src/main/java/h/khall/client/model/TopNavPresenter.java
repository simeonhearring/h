package h.khall.client.model;

public class TopNavPresenter extends AbstractPresenter<TopNavPresenter.Display>
{
  public TopNavPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}