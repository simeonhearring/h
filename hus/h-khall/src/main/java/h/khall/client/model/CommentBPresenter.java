package h.khall.client.model;

public class CommentBPresenter extends AbstractPresenter<CommentBPresenter.Display>
{
  public CommentBPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}