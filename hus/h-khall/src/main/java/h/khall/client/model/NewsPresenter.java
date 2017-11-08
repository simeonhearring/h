package h.khall.client.model;

public class NewsPresenter extends AbstractPresenter<NewsPresenter.Display>
{
  public NewsPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}