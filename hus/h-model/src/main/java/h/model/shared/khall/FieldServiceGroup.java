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
  public static final int[] ID =
  {
      0, -1, -2, -3
  };

  public static final String[] NAMES =
  {
      "-MOVEOUT-", "-PIONEERS-", "-ELDERS & SERVANTS-", "-CONGREGATION-"
  };

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
    return isMoveout(getId());
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

  public static boolean isMoveout(Integer inId)
  {
    return inId.intValue() == 0;
  }

  public static boolean isPioneers(Integer inId)
  {
    return inId.intValue() == ID[1];
  }

  public static boolean isElderOrServant(Integer inId)
  {
    return inId.intValue() == ID[2];
  }

  public static boolean isCongregation(Integer inId)
  {
    return inId.intValue() == ID[3];
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
          if (inPublisherId.equals(value.getIdLong()))
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
    ret.setId(ID[0]);
    ret.setTitle(NAMES[0]);
    return ret;
  }

  public static FieldServiceGroup pioneers(Integer inCongregation)
  {
    FieldServiceGroup ret = new FieldServiceGroup();
    ret.setId(ID[1]);
    ret.setTitle(NAMES[1]);
    return ret;
  }

  public static FieldServiceGroup eldersAndservants(Integer inCongregation)
  {
    FieldServiceGroup ret = new FieldServiceGroup();
    ret.setId(ID[2]);
    ret.setTitle(NAMES[2]);
    return ret;
  }

  public static FieldServiceGroup congregation(Integer inCongregation)
  {
    FieldServiceGroup ret = new FieldServiceGroup();
    ret.setId(ID[3]);
    ret.setTitle(NAMES[3]);
    return ret;
  }

  @JsonIgnore
  public boolean isDefault()
  {
    return mId != null && mId.intValue() < 1;
  }
}
