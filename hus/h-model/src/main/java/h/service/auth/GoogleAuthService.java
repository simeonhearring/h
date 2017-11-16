package h.service.auth;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import h.service.http.HttpService;

public class GoogleAuthService implements AuthService
{
  // https://code.google.com/apis/console/#project:759382168453
  private static final String TOKE = "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=";
  private static final String USER = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

  private static final Logger LOGGER = Logger.getLogger(GoogleAuthService.class);

  private HttpService mHttpService;

  @Override
  public String authenticate(String inToken)
  {
    LOGGER.info(mHttpService.get(TOKE + inToken));

    String ret = mHttpService.get(USER + inToken);
    LOGGER.info(ret);

    return jsonemail(ret);
  }

  private String jsonemail(String inText)
  {
    String ret = null;
    try
    {
      JSONObject json = new JSONObject(inText);
      ret = json.getString("email");
      LOGGER.info(ret);
    }
    catch (JSONException e)
    {
      LOGGER.error("ERROR JSON", e);
    }
    return ret;
  }

  public void setHttpService(HttpService inHttpService)
  {
    mHttpService = inHttpService;
  }
}