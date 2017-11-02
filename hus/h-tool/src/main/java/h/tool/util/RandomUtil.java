package h.tool.util;

import java.util.Random;

public final class RandomUtil
{
  private static final String S123 = "0123456789";
  private static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String SYMBOL = "~@#$%^&*()_+=-{}|[]"; // exclude <>/\
                                                              // !?,.;':

  private static final String ABC123 = ABC + S123 + ABC.toLowerCase();
  private static final String AB123SYM = ABC123 + SYMBOL;

  public static String random()
  {
    return random(12);
  }

  public static String random(int inLength)
  {
    return random(inLength, AB123SYM);
  }

  public static byte[] randomBytes(int inLength)
  {
    return random(inLength, AB123SYM).getBytes();
  }

  public static String authCode(int inLength)
  {
    return random(inLength, ABC123);
  }

  public static String coupon()
  {
    return random(7, ABC123);
  }

  private static String random(int inLength, String inChoices)
  {
    Random rnd = new Random();

    StringBuilder sb = new StringBuilder(inLength);
    for (int i = 0; i < inLength; i++)
    {
      sb.append(inChoices.charAt(rnd.nextInt(inChoices.length())));
    }

    return sb.toString();
  }

  public static String random(String[] inArray)
  {
    Random rnd = new Random();
    return inArray[rnd.nextInt(inArray.length - 1)];
  }

  public static <E> E random(E[] inArray)
  {
    Random rnd = new Random();
    return inArray[rnd.nextInt(inArray.length - 1)];
  }
}
