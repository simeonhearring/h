package h.khall.client.model;

public class CommentPresenter extends AbstractPresenter<CommentPresenter.Display>
{
  public CommentPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}