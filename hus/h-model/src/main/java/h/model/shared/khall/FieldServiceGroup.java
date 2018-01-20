package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
public class FieldServiceGroup implements Comparator<FieldServiceGroup>, Serializable
{
  private static final FieldServiceGroup MOVEOUT = initMoveout();

  private Integer mId;
  private String mTitle;
  private String mLocation;
  private int mCount;

  @JsonIgnore
  public Integer getId()
  {
    return mId;
  }

  public void setId(Integer inId)
  {
    mId = inId;
  }

  public void setLocation(String inLocation)
  {
    mLocation = inLocation;
  }

  public String getLocation()
  {
    return mLocation;
  }

  public void setTitle(String inTitle)
  {
    mTitle = inTitle;
  }

  public String getTitle()
  {
    return mTitle;
  }

  public void setCount(int inCount)
  {
    mCount = inCount;
  }

  @JsonIgnore
  public int getCount()
  {
    return mCount;
  }

  @JsonIgnore
  public String getInfo()
  {
    return mTitle;
  }

  @JsonIgnore
  public boolean isMoveout()
  {
    return getId() == 0;
  }

  @JsonIgnore
  public boolean isPioneers()
  {
    return isPioneers(getId());
  }

  @JsonIgnore
  public boolean isElderOrServant()
  {
    return isElderOrServant(getId());
  }

  @JsonIgnore
  public boolean isCongregation()
  {
    return isCongregation(getId());
  }

  public static boolean isPioneers(Integer inId)
  {
    return inId.intValue() == -1;
  }

  public static boolean isElderOrServant(Integer inId)
  {
    return inId.intValue() == -2;
  }

  public static boolean isCongregation(Integer inId)
  {
    return inId.intValue() == -3;
  }

  private boolean isGroup(Person inValue)
  {
    return inValue.getFsgId().equals(getId());
  }

  @Override
  public int compare(FieldServiceGroup inO1, FieldServiceGroup inO2)
  {
    return inO1.getTitle().compareTo(inO2.getTitle());
  }

  public static List<Person> filter(Collection<Person> inList,
      FieldServiceGroup inFieldServiceGroup)
  {
    return filter(inList, inFieldServiceGroup, null);
  }

  public static List<Person> filter(Collection<Person> inList,
      FieldServiceGroup inFieldServiceGroup, Long inPublisherId)
  {
    List<Person> ret = new ArrayList<>();

    if (inList != null)
    {
      for (Person value : inList)
      {
        if (inPublisherId != null)
        {
          if (inPublisherId.equals(value.getId()))
          {
            ret.add(value);
            break;
          }
        }
        else if (inFieldServiceGroup.isCongregation())
        {
          ret.add(value);
        }
        else if (inFieldServiceGroup.isElderOrServant() && (value.isElder() || value.isServant()))
        {
          ret.add(value);
        }
        else if (inFieldServiceGroup.isPioneers() && value.isRegular())
        {
          ret.add(value);
        }
        else
        {
          if (inFieldServiceGroup.isGroup(value))
          {
            ret.add(value);
          }
        }
      }
    }
    return ret;
  }

  public static FieldServiceGroup getMoveout()
  {
    return MOVEOUT;
  }

  public static FieldServiceGroup getSorter()
  {
    return MOVEOUT;
  }

  private static FieldServiceGroup initMoveout()
  {
    FieldServiceGroup ret = new FieldServiceGroup();
    ret.setId(0);
    ret.setTitle("-MOVEOUT-");
    return ret;
  }

  public static FieldServiceGroup pioneers(Integer inCongregation)
  {
    FieldServiceGroup ret = new FieldServiceGroup();
    ret.setId(-1);
    ret.setTitle("-PIONEERS-");
    return ret;
  }

  public static FieldServiceGroup eldersAndservants(Integer inCongregation)
  {
    FieldServiceGroup ret = new FieldServiceGroup();
    ret.setId(-2);
    ret.setTitle("-ELDERS & SERVANTS-");
    return ret;
  }

  public static FieldServiceGroup congregation(Integer inCongregation)
  {
    FieldServiceGroup ret = new FieldServiceGroup();
    ret.setId(-3);
    ret.setTitle("-CONGREGATION-");
    return ret;
  }

  @JsonIgnore
  public boolean isDefault()
  {
    return mId != null && mId.intValue() < 1;
  }
}
