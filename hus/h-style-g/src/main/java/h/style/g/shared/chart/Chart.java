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

import h.model.shared.util.RandomUtil;

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
  private Stat mStat;

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

  public Dataset createDataset(String inLabel, Double... inData)
  {
    return mData.createDataset(inLabel, inData);
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

  public void update(String[] inLabel)
  {
    mData.addLabel(inLabel);
  }

  public void update(String inDatasetLabel, Double[] inData)
  {
    for (Dataset value : mData.mDatasets)
    {
      if (inDatasetLabel.equals(value.mLabel))
      {
        value.addData(inData);
      }
    }
  }

  public void format(Dataset inSet)
  {
    String c1 = "hsla(" + RandomUtil.randomInt(360) + ",90%,90%,";
    inSet.setBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setBorderColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    // inSet.setPointBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setPointBorderColor("#fff");
  }

  public Stat getStat()
  {
    return mStat;
  }

  public void setStat(Stat inStat)
  {
    mStat = inStat;
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

    public Dataset createDataset(String inLabel, Double[] inData)
    {
      Dataset ret = new Dataset();
      ret.setLabel(inLabel);
      ret.addData(inData);
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
      mLabels.clear();
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

    public void addData(Double... inDouble)
    {
      mData.clear();
      for (Double value : inDouble)
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

  public static class Stat implements Serializable
  {
    private String mHead;
    private String mSubHead;
    private String mFooter;
    private String mTopRight;

    public String getHead()
    {
      return mHead;
    }

    public void setHead(String inHead)
    {
      mHead = inHead;
    }

    public String getSubHead()
    {
      return mSubHead;
    }

    public void setSubHead(String inSubHead)
    {
      mSubHead = inSubHead;
    }

    public String getFooter()
    {
      return mFooter;
    }

    public void setFooter(String inFooter)
    {
      mFooter = inFooter;
    }

    public String getTopRight()
    {
      return mTopRight;
    }

    public void setTopRight(String inTopRight)
    {
      mTopRight = inTopRight;
    }
  }
}