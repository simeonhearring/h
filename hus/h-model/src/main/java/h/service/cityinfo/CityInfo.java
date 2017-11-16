package h.service.cityinfo;

import java.util.List;

public class CityInfo
{
  private List<City> mCities;

  public List<City> getCities()
  {
    return mCities;
  }

  public static class City
  {
    private String mCity;
    private String mState;
    private String mZip;
    private String mTimeZone;
    private String mAreaCode;

    public String getCity()
    {
      return mCity;
    }

    public String getState()
    {
      return mState;
    }

    public String getZip()
    {
      return mZip;
    }

    public String getTimeZone()
    {
      return mTimeZone;
    }

    public String getAreaCode()
    {
      return mAreaCode;
    }
  }
}
