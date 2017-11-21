package h.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper
{
  private ObjectMapper mMapper;

  public JsonMapper()
  {
    mMapper = new ObjectMapper();
    mMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public ObjectMapper getMapper()
  {
    return mMapper;
  }

  public String writeValue(Object inModel)
  {
    try
    {
      return mMapper.writeValueAsString(inModel);
    }
    catch (JsonProcessingException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  public <M> M readValue(String inText, Class<M> inClass)
  {
    try
    {
      return mMapper.readValue(inText, inClass);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}