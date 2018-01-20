package h.khall.client.model;

import java.util.List;

import h.model.shared.khall.Person;
import h.model.shared.khall.Roles;
import h.model.shared.khall.Roles.Role;

public class RolePresenter extends HasGLabelPresenter<Role>
{
  public RolePresenter(Display inDisplay)
  {
    super(inDisplay);
  }

  @Override
  String name()
  {
    return "Roles";
  }

  @Override
  Role[] values()
  {
    return Roles.Role.values();
  }

  @Override
  List<Role> values(Person inPerson)
  {
    return inPerson.getRoles().getRoles();
  }

  @Override
  protected boolean validate(Person inPerson)
  {
    boolean ret = !inPerson.getFsgId().equals(0);
    if (!ret)
    {
      mDisplay.notify(
          "To change roles, " + inPerson.gName() + " must be assigned to a field service group.");
    }
    return ret;
  }

  public interface Display extends HasGLabelPresenter.Display
  {
  }
}