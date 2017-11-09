package h.khall.client.model;

public class PersonLookupPresenter extends AbstractPresenter<PersonLookupPresenter.Display>
{
  public PersonLookupPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}