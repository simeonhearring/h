package h.model.shared;

public enum Params
{
  debug;

  public enum Boolean
  {
    Y,
    Yes,
    N,
    No,
    True,
    False;
  }

  public enum Debug
  {
    g,
    c,
    t,
    gc,
    ct,
    gt,
    gct;

    public static String name(Debug inDebug)
    {
      return inDebug != null ? inDebug.name() : null;
    }

    public static String[] names()
    {
      String[] ret = new String[Debug.values().length];
      for (int i = 0; i < ret.length; i++)
      {
        ret[i] = Debug.values()[i].name();
      }
      return ret;
    }
  }
}