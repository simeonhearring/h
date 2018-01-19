package h.model.shared.khall;

import h.model.shared.util.StringUtil;

public enum Hall implements HasGLabel
{
  MAIN,
  SECOND;

  public String school()
  {
    return ordinal() + 1 + ",";
  }

  @Override
  public String gLabel()
  {
    return StringUtil.toTitle(name());
  }
}