package h.khall.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Roles implements Serializable
{
  public enum Role
  {
    STUDENT,
    ANOITED,
    PUBLISHER,
    AUXILIARY_PIONEER,
    AUXILIARY_PIONEER_30,
    REGULAR_PIONEER,
    SPECIAL_PIONEER,
    MINISTERIAL_SERVANT,
    ELDER,
    GROUP_ASSISTANT,
    GROUP_SERVANT,
    GROUP_OVERSEER;
  }

  private List<Role> mRoles;

  public void normalize()
  {
    if (mRoles == null)
    {
      mRoles = new ArrayList<>();
    }
  }

  public List<Role> getRoles()
  {
    return mRoles;
  }

  public void setRoles(List<Role> inRoles)
  {
    mRoles = inRoles;
  }

  public boolean contains(Role inRole)
  {
    return mRoles.contains(inRole);
  }

  public void add(Role inRole)
  {
    mRoles.add(inRole);
  }
}