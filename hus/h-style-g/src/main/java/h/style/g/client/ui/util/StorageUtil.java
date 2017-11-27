package h.style.g.client.ui.util;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;

/*
 *  https://developers.google.com/web-toolkit/doc/latest/DevGuideHtml5Storage
 */

public final class StorageUtil
{
  public static final String EKEY = "HUS.SEC.#eK4p";

  public static boolean addEncryptKey(String inNumber, String inKey)
  {
    Storage storage = Storage.getLocalStorageIfSupported();
    boolean canStore = storage != null;
    if (canStore)
    {
      if (hasEncrypt())
      {
        JSONObject o = get();
        o.put(inNumber, new JSONString(inKey));
        storage.setItem(EKEY, JsniUtil.stringify(o.getJavaScriptObject()));
      }
      else
      {
        JSONObject o = new JSONObject();
        o.put("default", new JSONString(inNumber));
        o.put(inNumber, new JSONString(inKey));
        storage.setItem(EKEY, JsniUtil.stringify(o.getJavaScriptObject()));
      }
    }
    return canStore;
  }

  public static void clearEncryptKey()
  {
    Storage storage = Storage.getLocalStorageIfSupported();
    storage.removeItem(EKEY);
  }

  public static JSONObject get()
  {
    return new JSONObject(JsniUtil.parse(getEncryptKey()));
  }

  private static String getEncryptKey()
  {
    String ret = null;
    Storage storage = Storage.getLocalStorageIfSupported();
    if (storage != null)
    {
      ret = storage.getItem(EKEY);
    }
    return ret;
  }

  public static boolean hasEncrypt()
  {
    boolean ret = false;
    Storage storage = Storage.getLocalStorageIfSupported();
    if (storage != null)
    {
      StorageMap map = new StorageMap(storage);
      ret = map.containsKey(EKEY);
    }
    return ret;
  }

  public static boolean hasEncryptKey(String inNumber)
  {
    return getEncryptKey(inNumber) != null;
  }

  public static String getEncryptKey(String inNumber)
  {
    String ret = null;

    try
    {
      JSONObject o = get();
      if (inNumber == null)
      {
        String d = o.get("default").isString().stringValue();
        ret = o.get(d).isString().stringValue();
      }
      else
      {
        if (o.containsKey(inNumber))
        {
          ret = o.get(inNumber).isString().stringValue();
        }
      }
    }
    catch (Exception e)
    {
    }

    return ret;
  }
}