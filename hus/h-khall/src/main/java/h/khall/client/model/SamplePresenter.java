package h.khall.client.model;

public class SamplePresenter extends AbstractPresenter<SamplePresenter.Display>
{
  public SamplePresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}