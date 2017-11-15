package h.khall.client.model;

public class PersonInfoPresenter extends AbstractPresenter<PersonInfoPresenter.Display>
{
  public PersonInfoPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}