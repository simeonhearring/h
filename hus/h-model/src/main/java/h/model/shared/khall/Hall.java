package h.model.shared.khall;

import h.model.shared.util.StringUtil;

public enum Hall
{
  MAIN,
  SECOND;

  public String school()
  {
    return ordinal() + 1 + ",";
  }

  public String getLabel()
  {
    return StringUtil.toTitle(name());
  }
}