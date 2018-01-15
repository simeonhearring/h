package h.khall.client.ui;

import h.model.shared.khall.Roles.Role;

public class TakesPioneer extends TakesEnum<Role>
{
  public TakesPioneer()
  {
    addItem("-Select-", "");
    addItem("Auxiliary 30", Role.AUXILIARY_PIONEER_30.name());
    addItem("Auxiliary", Role.AUXILIARY_PIONEER.name());
    addItem("Regular", Role.REGULAR_PIONEER.name());
    setEnums(Role.AUXILIARY_PIONEER_30, Role.AUXILIARY_PIONEER, Role.REGULAR_PIONEER);
  }
}