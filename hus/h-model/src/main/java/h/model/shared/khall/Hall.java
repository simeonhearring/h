package h.model.shared.khall;

public enum Hall
{
  MAIN,
  AUX1,
  AUX2;

  public String school()
  {
    return ordinal() + 1 + ",";
  }
}