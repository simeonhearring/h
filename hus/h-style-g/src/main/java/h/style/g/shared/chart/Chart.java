package h.style.g.shared.chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.dev.util.collect.HashMap;

public class Chart
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

  public static class Data
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

    public void addLabel(String... inText)
    {
      for (String value : inText)
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

  public static class Dataset
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

  public static class Options
  {
    private Boolean mResponsive;

    public Boolean getResponsive()
    {
      return mResponsive;
    }
  }

  @SuppressWarnings("serial")
  public static class Labels extends ArrayList<String>
  {
  }

  @SuppressWarnings("serial")
  public static class Datasets extends ArrayList<Dataset>
  {
  }

  public static Chart sample()
  {
    Chart ret = new Chart(Type.LINE);

    ret.mOptions.mResponsive = true;

    Data d = ret.mData;

    d.addLabel("January", "February", "March", "April", "May", "June", "July");

    Dataset ds1 = d.createDataset("Example dataset1");

    ds1.setBackgroundColor("rgba(26,179,148,0.5)");
    ds1.setBorderColor("rgba(26,179,148,0.7)");
    ds1.setPointBackgroundColor("rgba(26,179,148,1)");
    ds1.setPointBorderColor("#fff");
    ds1.addData(28, 48, 40, 19, 86, 27, 90);

    Dataset ds2 = d.createDataset("Example dataset2");

    ds2.setBackgroundColor("rgba(220,220,220,0.5)");
    ds2.setBorderColor("rgba(220,220,220,1)");
    ds2.setPointBackgroundColor("rgba(220,220,220,1)");
    ds2.setPointBorderColor("#fff");
    ds2.addData(65, 59, 80, 81, 56, 55, 40);

    return ret;
  }
}