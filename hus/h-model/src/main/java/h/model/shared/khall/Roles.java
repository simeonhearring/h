package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import h.model.shared.util.StringUtil;

@SuppressWarnings("serial")
public class Roles implements Serializable
{
  public enum Role implements HasGLabel
  {
    ANOITED,
    STUDENT,
    PUBLISHER,
    AUXILIARY_PIONEER_30,
    AUXILIARY_PIONEER,
    REGULAR_PIONEER,
    SPECIAL_PIONEER,
    MINISTERIAL_SERVANT,
    ELDER,
    GROUP_ASSISTANT,
    GROUP_SERVANT,
    GROUP_OVERSEER,
    LIFE_AND_MINISTRY_OVERSEER,
    AUXILIARY_COUNSELOR,
    WATCHTOWER_CONDUCTOR,
    SERVICE_OVERSEER,
    SECRETARY,
    COORDINATOR;

    @Override
    public String gLabel()
    {
      return StringUtil.toTitle(name().replaceAll("_", " "));
    }
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

  public Role gPrimary()
  {
    Role ret = Role.STUDENT;

    for (Role value : mRoles)
    {
      if (ret.ordinal() < value.ordinal())
      {
        ret = value;
      }
    }
    return ret;
  }
}