package h.service.cityinfo;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

import h.service.cityinfo.CityInfo.City;
import h.service.http.HttpService;

public class GetCityInfoService implements CityInfoService
{
  private static final Logger LOGGER = Logger.getLogger(GetCityInfoService.class);

  private static final String SERVICE = "http://www.webservicex.net/uszip.asmx/";

  private static final String ZIPCODE = SERVICE + "GetInfoByZIP?USZip=";
  private static final String AREA = SERVICE + "GetInfoByAreaCode?USAreaCode=";
  private static final String CITY = SERVICE + "GetInfoByCity?USCity=";
  private static final String STATE = SERVICE + "GetInfoByState?USState=";

  private HttpService mHttpService;

  public void setHttpService(HttpService inHttpService)
  {
    mHttpService = inHttpService;
  }

  @Override
  public CityInfo byZipCode(String inZipCode)
  {
    return get(ZIPCODE + inZipCode);
  }

  @Override
  public CityInfo byAreaCode(String inAreaCode)
  {
    return get(AREA + inAreaCode);
  }

  @Override
  public CityInfo byCity(String inCity)
  {
    return get(CITY + inCity);
  }

  @Override
  public CityInfo byState(String inState)
  {
    return get(STATE + inState);
  }

  private CityInfo get(String inUrl)
  {
    CityInfo ret = null;
    try
    {
      ret = transform(mHttpService.get(inUrl));
    }
    catch (Exception e)
    {
      LOGGER.error("ERROR TRANSFORM/HTTP", e);
    }
    return ret;
  }

  private static CityInfo transform(String inXml)
  {
    XStream xStream = new XStream();

    xStream.alias("NewDataSet", CityInfo.class);

    xStream.addImplicitCollection(CityInfo.class, "mCities", "Table", City.class);

    xStream.aliasField("CITY", City.class, "mCity");
    xStream.aliasField("STATE", City.class, "mState");
    xStream.aliasField("ZIP", City.class, "mZip");
    xStream.aliasField("AREA_CODE", City.class, "mAreaCode");
    xStream.aliasField("TIME_ZONE", City.class, "mTimeZone");

    return (CityInfo) xStream.fromXML(inXml);
  }
}
