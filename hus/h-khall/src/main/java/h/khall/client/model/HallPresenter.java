package h.khall.client.model;

import java.util.List;

import h.model.shared.khall.Hall;
import h.model.shared.khall.Person;

public class HallPresenter extends HasGLabelPresenter<Hall>
{
  public HallPresenter(Display inDisplay)
  {
    super(inDisplay);
  }

  @Override
  String name()
  {
    return "Assignable Halls";
  }

  @Override
  Hall[] values()
  {
    return Hall.values();
  }

  @Override
  List<Hall> values(Person inPerson)
  {
    return inPerson.getHalls();
  }

  public interface Display extends HasGLabelPresenter.Display
  {
  }
}