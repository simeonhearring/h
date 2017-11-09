package h.khall.client.model;

public class AssignPresenter extends AbstractPresenter<AssignPresenter.Display>
{
  public AssignPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}