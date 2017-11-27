package h.khall.server.dao.bean;

import java.util.Random;

public class RandomName
{
  private final static String[] FIRST =
  {
      "Simeon",
      "Nadia",
      "Jathen",
      "Aidan",
      "Alania",
      "Shane",
      "LeShaunte"
  };
  private final static String[] LAST =
  {
      "Hearring",
      "Butler",
      "Humphrey",
      "O'Bard"
  };
  private final static Random RAND = new Random();

  public String first()
  {
    return FIRST[RAND.nextInt(FIRST.length)];
  }

  public String last()
  {
    return LAST[RAND.nextInt(LAST.length)];
  }

  public int nextInt(int inI)
  {
    return RAND.nextInt(inI);
  }

  public String name()
  {
    return last() + ", " + first();
  }
}
