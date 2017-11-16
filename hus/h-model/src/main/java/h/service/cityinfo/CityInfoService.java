package h.service.cityinfo;

public interface CityInfoService
{
  CityInfo byZipCode(String inZipCode);

  CityInfo byState(String inState);

  CityInfo byCity(String inCity);

  CityInfo byAreaCode(String inAreaCode);
}