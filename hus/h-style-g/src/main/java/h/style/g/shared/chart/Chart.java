package h.style.g.shared.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// TODO is this in the right package.
/*
 * http://www.chartjs.org/
 */

@SuppressWarnings("serial")
public class Chart implements Serializable
{
  public enum Type
  {
    LINE("line"),
    BAR("bar");

    private String mCode;

    private Type(String inCode)
    {
      mCode = inCode;
    }

    public String getCode()
    {
      return mCode;
    }
  }

  private String mDataType;
  private Type mType;
  private Data mData;
  private Options mOptions;

  private Chart()
  {
  }

  public Chart(Type inType)
  {
    this();
    mType = inType;
    mData = new Data();
    mOptions = new Options();
  }

  public void setResponsive(boolean inResponsive)
  {
    mOptions.mResponsive = inResponsive;
  }

  public void addLabel(String... inLabels)
  {
    mData.addLabel(inLabels);
  }

  public void addLabel(String inLabel)
  {
    mData.addLabel(inLabel);
  }

  public Dataset createDataset(String inLabel)
  {
    return mData.createDataset(inLabel);
  }

  public String getCode()
  {
    return mType.mCode;
  }

  public Data getData()
  {
    return mData;
  }

  public Options getOptions()
  {
    return mOptions;
  }

  public String getDataType()
  {
    return mDataType;
  }

  public void setDataType(Enum<?> inDataType)
  {
    mDataType = inDataType.name();
  }

  public static class Data implements Serializable
  {
    private Labels mLabels;
    private Datasets mDatasets;

    public Data()
    {
      mLabels = new Labels();
      mDatasets = new Datasets();
    }

    public Dataset createDataset(String inLabel)
    {
      Dataset ret = new Dataset();
      ret.setLabel(inLabel);
      addDataset(ret);
      return ret;
    }

    public void addDataset(Dataset ret)
    {
      mDatasets.add(ret);
    }

    public void addLabel(String inText)
    {
      mLabels.add(inText);
    }

    public void addLabel(String... inLabels)
    {
      for (String value : inLabels)
      {
        mLabels.add(value);
      }
    }

    public Labels getLabels()
    {
      return mLabels;
    }

    public Datasets getDatasets()
    {
      return mDatasets;
    }
  }

  public static class Dataset implements Serializable
  {
    private String mLabel;
    private String mBackgroundColor;
    private String mBorderColor;
    private String mPointBackgroundColor;
    private String mPointBorderColor;
    private List<Double> mData;

    public Dataset()
    {
      mData = new ArrayList<>();
    }

    public Map<String, String> data()
    {
      Map<String, String> ret = new HashMap<>();
      ret.put("label", mLabel);
      ret.put("backgroundColor", mBackgroundColor);
      ret.put("borderColor", mBorderColor);
      ret.put("pointBackgroundColor", mPointBackgroundColor);
      ret.put("pointBorderColor", mPointBorderColor);
      return ret;
    }

    public void addData(double... inDouble)
    {
      for (double value : inDouble)
      {
        mData.add(value);
      }
    }

    public void setLabel(String inLabel)
    {
      mLabel = inLabel;
    }

    public void setBackgroundColor(String inBackgroundColor)
    {
      mBackgroundColor = inBackgroundColor;
    }

    public void setBorderColor(String inBorderColor)
    {
      mBorderColor = inBorderColor;
    }

    public void setPointBackgroundColor(String inPointBackgroundColor)
    {
      mPointBackgroundColor = inPointBackgroundColor;
    }

    public void setPointBorderColor(String inPointBorderColor)
    {
      mPointBorderColor = inPointBorderColor;
    }

    public List<Double> getData()
    {
      return mData;
    }

    public void setData(List<Double> inData)
    {
      mData = inData;
    }
  }

  public static class Options implements Serializable
  {
    private Boolean mResponsive;

    public Boolean getResponsive()
    {
      return mResponsive;
    }
  }

  public static class Labels extends ArrayList<String> implements Serializable
  {
  }

  public static class Datasets extends ArrayList<Dataset> implements Serializable
  {
  }
}