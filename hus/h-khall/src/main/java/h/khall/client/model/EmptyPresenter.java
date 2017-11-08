package h.khall.client.model;

public class EmptyPresenter extends AbstractPresenter<EmptyPresenter.Display>
{
  public EmptyPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}