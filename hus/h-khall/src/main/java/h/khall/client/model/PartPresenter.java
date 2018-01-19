package h.khall.client.model;

import java.util.List;

import h.model.shared.khall.Part;
import h.model.shared.khall.Person;

public class PartPresenter extends HasGLabelPresenter<Part>
{
  public PartPresenter(Display inDisplay)
  {
    super(inDisplay);
  }

  @Override
  String name()
  {
    return "Assignable Parts";
  }

  @Override
  Part[] values()
  {
    return Part.assignable();
  }

  @Override
  List<Part> values(Person inPerson)
  {
    return inPerson.getParts();
  }

  public interface Display extends HasGLabelPresenter.Display
  {
  }
}