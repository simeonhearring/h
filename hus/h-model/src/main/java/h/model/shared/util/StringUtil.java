package h.model.shared.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil
{
  StringUtil()
  {
  }

  public static boolean isEmpty(String inValue)
  {
    return inValue == null || "".equals(inValue);
  }

  public static boolean isEqual(String inString, Object inObject)
  {
    return inString == inObject || inString != null && inString.equals(inObject);
  }

  public static String lpad(Object inValue, String inPad, int inMaxLength)
  {
    if (inPad == null)
    {
      return "";
    }

    if (inValue == null)
    {
      inValue = "";
    }

    String value = String.valueOf(inValue);

    for (int i = value.length(); i < inMaxLength; i++)
    {
      value = inPad + value;
    }

    return value;
  }

  public static String toTitle(String inString)
  {
    StringBuilder ret = new StringBuilder();
    if (inString != null)
    {
      for (String string : inString.split(" "))
      {
        ret.append(toTitleFirstWord(string.toLowerCase())).append(" ");
      }
    }
    return ret.toString().trim();
  }

  public static String toTitleFirstWord(String inString)
  {
    if (inString == null || "".equals(inString))
    {
      return "";
    }

    String s = inString.substring(0, 1).toUpperCase();

    if (inString.length() == 1)
    {
      return s;
    }

    return s + inString.substring(1);
  }

  public static boolean isValid(String inString)
  {
    return inString != null && !"".equals(inString.trim());
  }

  public static String trim(String inString, int inTrimAt)
  {
    if (!isValid(inString))
    {
      return "";
    }

    return inString.length() <= inTrimAt ? inString : inString.substring(0, inTrimAt);
  }

  public static String join(String[] inStrings, String inDelim)
  {
    StringBuilder ret = new StringBuilder();

    if (inDelim == null)
    {
      inDelim = "";
    }

    if (inStrings != null && inStrings.length > 0)
    {
      for (String s : inStrings)
      {
        ret.append(s);
        ret.append(inDelim);
      }

      ret.delete(ret.length() - inDelim.length(), ret.length());
    }

    return ret.toString();
  }

  public static String spaceSeparated(String... inStrings)
  {
    StringBuilder result = new StringBuilder();
    for (String s : inStrings)
    {
      s = s == null ? "" : s.trim();
      result.append(result.length() == 0 ? s : spacePrefixed(s));
    }
    return result.toString();
  }

  public static String spacePrefixed(String inText)
  {
    String result = "";
    if (inText != null)
    {
      result = inText.trim();
      if (result.length() > 0)
      {
        result = " " + result;
      }
    }
    return result;
  }

  public static String repeat(String inTextToRepeat, String inSeparator, int inRepeatCount)
  {
    if (inTextToRepeat == null)
    {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < inRepeatCount; i++)
    {
      if (sb.length() > 0)
      {
        sb.append(inSeparator);
      }
      sb.append(inTextToRepeat);
    }
    return sb.toString();
  }

  public static String toValue(String inString)
  {
    return inString == null ? "" : inString;
  }

  public static String toString(Integer inInteger)
  {
    return inInteger == null ? null : String.valueOf(inInteger);
  }

  public static String toString(Double inDouble)
  {
    return inDouble == null ? null : String.valueOf(inDouble);
  }

  public static String toString(Long inLong)
  {
    return inLong == null ? null : String.valueOf(inLong);
  }

  public static String toValue(Integer inInteger)
  {
    return inInteger == null ? "" : String.valueOf(inInteger);
  }

  public static String toString(List<String> inValue)
  {
    if (inValue == null)
    {
      return null;
    }

    boolean b = false;
    StringBuilder sb = new StringBuilder();
    for (String object : inValue)
    {
      if (object != null)
      {
        b = true;
        sb.append(object).append(",");
      }
    }
    return b ? sb.toString() : null;
  }

  public static String toString(String... inValue)
  {
    if (inValue == null)
    {
      return null;
    }

    boolean b = false;
    StringBuilder sb = new StringBuilder();
    for (String object : inValue)
    {
      if (object != null)
      {
        b = true;
        sb.append(object).append(",");
      }
    }
    return b ? sb.toString() : null;
  }

  public static List<String> toList(String inValue)
  {
    if (inValue == null)
    {
      return null;
    }

    List<String> ret = new ArrayList<>();

    if (inValue.indexOf(",") != -1)
    {
      for (String value : inValue.split(","))
      {
        if (value != null)
        {
          ret.add(value);
        }
      }
    }

    return ret = ret.size() != 0 ? ret : null;
  }

  public static String[] toArray(String inValue)
  {
    if (inValue == null)
    {
      return null;
    }

    List<String> list = toList(inValue);

    String[] ret = new String[list.size()];
    int i = 0;
    for (String string : list)
    {
      ret[i] = string;
      i++;
    }

    return ret;
  }

  public static boolean isInValid(String... inString)
  {
    boolean ret = false;
    for (String string : inString)
    {
      ret |= string == null || "".equals(string.trim());
    }
    return ret;
  }

  public static boolean isValidName(String inName)
  {
    return isValidName(inName, 3);
  }

  public static boolean isValidName(String inName, int inMinLength)
  {
    if (inName == null)
    {
      return false;
    }
    return inName.trim().length() > inMinLength;
  }

  public static String escapeHtml(String html)
  {
    if (html == null)
    {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
  }

  public static String formatCardNumber(String inCard)
  {
    inCard = inCard.replaceAll("[^0-9]+", "");
    int length = inCard.length();
    inCard = length > 16 ? inCard.substring(0, 16) : inCard;
    length = inCard.length();

    StringBuilder sb = new StringBuilder();
    if (length > 4 && length <= 8)
    {
      sb.append(inCard.substring(0, 4)).append(" ");
      sb.append(inCard.substring(4, length));
    }
    else if (length > 8 && length <= 12)
    {
      sb.append(inCard.substring(0, 4)).append(" ");
      sb.append(inCard.substring(4, 8)).append(" ");
      sb.append(inCard.substring(8, length));
    }
    else if (length > 12 && length <= 16)
    {
      sb.append(inCard.substring(0, 4)).append(" ");
      sb.append(inCard.substring(4, 8)).append(" ");
      sb.append(inCard.substring(8, 12)).append(" ");
      sb.append(inCard.substring(12, length));
    }

    inCard = sb.length() == 0 ? inCard : sb.toString();
    return inCard;
  }

  public static String formatPhoneNumber(String inPhone)
  {
    inPhone = inPhone.replaceAll("[^0-9]+", "");
    int length = inPhone.length();
    inPhone = length > 10 ? inPhone.substring(0, 10) : inPhone;
    length = inPhone.length();

    StringBuilder sb = new StringBuilder();
    if (length > 3 && length <= 6)
    {
      sb.append("(").append(inPhone.substring(0, 3)).append(") ");
      sb.append(inPhone.substring(3, length));
    }
    else if (length > 6 && length <= 10)
    {
      sb.append("(").append(inPhone.substring(0, 3)).append(") ");
      sb.append(inPhone.substring(3, 6));
      sb.append("-").append(inPhone.substring(6, length));
    }

    inPhone = sb.length() == 0 ? inPhone : sb.toString();
    return inPhone;
  }

  public static String numberOnly(String inText)
  {
    return inText.replaceAll("[^0-9]+", "");
  }

  public static String formatZipCode(String inText)
  {
    String ret = numberOnly(inText);
    if (ret.length() > 5)
    {
      ret = ret.substring(0, 5);
    }
    return ret;
  }

  public static String amountOnly(String inText)
  {
    int pos = inText.indexOf(".");
    StringBuilder sb = new StringBuilder(inText.replaceAll("[^0-9]+", ""));
    if (pos != -1)
    {
      sb.insert(pos, ".");
    }
    return sb.toString();
  }

  public static String nullIfEmpty(String inValue)
  {
    String trim = inValue.trim();
    return "".equals(trim) ? null : trim;
  }

  public static String ensure(Object inObject)
  {
    return inObject != null ? String.valueOf(inObject) : "";
  }

  public static String ensure(String inString, String inPrefix)
  {
    return isValid(inString) ? inPrefix + String.valueOf(inString) : "";
  }

  public static String ensure(String inString, String inPrefix, String inSuffix)
  {
    return isValid(inString) ? inPrefix + String.valueOf(inString) + inSuffix : "";
  }

  public static String ensureS(String inString, String inSuffix)
  {
    return isValid(inString) ? String.valueOf(inString) + inSuffix : "";
  }

  public static boolean isPhone(String inPhone)
  {
    return isValid(inPhone) && isPhone(numberOnly(inPhone).length(), inPhone);
  }

  private static boolean isPhone(int inLength, String inPhone)
  {
    return inLength == 10 || inLength == 11 && inPhone.startsWith("1");
  }

  public static boolean isEmail(String inEmail)
  {
    return isValid(inEmail) && contains(inEmail, "@") && contains(inEmail, ".");
  }

  public static boolean contains(String inValue, String inContains)
  {
    return inValue.indexOf(inContains) != -1;
  }

  public static String substring(String inValue, int inIndex)
  {
    if (inValue == null)
    {
      return "";
    }
    int l = inValue.length(), i = l > inIndex ? inIndex : l;
    return inValue.substring(0, i);
  }

  public static String notBlank(String inText)
  {
    return inText != null && "".equals(inText.trim()) ? null : inText;
  }

  public static String notNull(String inText)
  {
    return isLikeNull(inText) ? "" : inText;
  }

  public static String notNull(String inText, String inSuffix)
  {
    return isLikeNull(inText) ? "" : inText + inSuffix;
  }

  public static String notNullArray(String inSuffix, String... inText)
  {
    StringBuilder ret = new StringBuilder();
    for (String value : inText)
    {
      if (isLikeNull(value))
      {
        ret.append(inText).append(inSuffix);
      }
    }
    return ret.toString();
  }

  private static boolean isLikeNull(String inText)
  {
    return inText == null || "null".equals(inText) || "".equals(inText);
  }

  public static List<String> toList(String... inValues)
  {
    List<String> ret = new ArrayList<>();
    for (String value : inValues)
    {
      ret.add(value);
    }
    return ret;
  }

  public static String emptyToNull(String inText)
  {
    return inText != null && inText.trim().length() == 0 ? null : inText;
  }
}