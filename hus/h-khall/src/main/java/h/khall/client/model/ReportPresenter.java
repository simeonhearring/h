package h.khall.client.model;

public class ReportPresenter extends AbstractPresenter<ReportPresenter.Display>
{
  public ReportPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}