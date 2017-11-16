package h.model.shared.util;

public class TextUtil extends StringUtil
{
  TextUtil()
  {
  }

  public static String toText(Enum<?> inEnum)
  {
    String ret = "";
    if (inEnum != null)
    {
      ret = toTitle(inEnum.name().replaceAll("_", " "));
    }
    return ret;
  }
}