package h.model.shared.khall;

public enum Hall
{
  MAIN,
  SECOND;

  public String school()
  {
    return ordinal() + 1 + ",";
  }
}