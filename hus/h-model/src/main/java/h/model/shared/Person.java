package h.model.shared;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import h.model.shared.util.StringUtil;
import h.model.shared.util.TimeUtil;

@SuppressWarnings("serial")
public class Person implements Serializable, Tag
{
  public enum Gender
  {
    Male,
    Female;
  }

  private Long mId;

  private String mFirst;
  private String mMiddle;
  private String mLast;
  private String mSuffix;
  private Gender mGender;
  private Date mBirth;
  private String mEmail;
  private String mMobile;
  private String mHome;

  private String[] mAddress = new String[5];

  public Person()
  {
  }

  public Person(String inLast, String inFirst)
  {
    mLast = inLast;
    mFirst = inFirst;
  }

  public boolean isEmail()
  {
    return StringUtil.isEmail(mEmail);
  }

  public void setAddress(String[] inAddress)
  {
    mAddress = inAddress;
  }

  public String[] getAddress()
  {
    return mAddress;
  }

  public void setId(Long inId)
  {
    mId = inId;
  }

  public String getFirst()
  {
    return mFirst;
  }

  public void setFirst(String inFirst)
  {
    mFirst = inFirst;
  }

  public String getMiddle()
  {
    return mMiddle;
  }

  public void setMiddle(String inMiddle)
  {
    mMiddle = inMiddle;
  }

  public String getLast()
  {
    return mLast;
  }

  public void setLast(String inLast)
  {
    mLast = inLast;
  }

  public String getSuffix()
  {
    return mSuffix;
  }

  public void setSuffix(String inSuffix)
  {
    mSuffix = inSuffix;
  }

  public Gender getGender()
  {
    return mGender;
  }

  public void setGender(Gender inGender)
  {
    mGender = inGender;
  }

  @JsonIgnore
  public boolean isGender(Gender inGender)
  {
    return inGender == null || inGender.equals(mGender);
  }

  @JsonIgnore
  public boolean isMale()
  {
    return Gender.Male.equals(mGender);
  }

  public Date getBirth()
  {
    return mBirth;
  }

  public void setBirth(Date inBirth)
  {
    mBirth = inBirth;
  }

  public String getEmail()
  {
    return mEmail;
  }

  public void setEmail(String inEmail)
  {
    mEmail = inEmail;
  }

  public String getMobile()
  {
    return mMobile;
  }

  public void setMobile(String inMobile)
  {
    mMobile = inMobile;
  }

  public String getHome()
  {
    return mHome;
  }

  public void setHome(String inHome)
  {
    mHome = inHome;
  }

  public String gAddress()
  {
    return StringUtil.ensure(gAddress1(), "", " ") + StringUtil.ensure(gAddress2(), "", " ")
        + StringUtil.ensure(gCity(), "", ", ") + StringUtil.ensure(gState(), "", ", ")
        + StringUtil.ensure(gZip());
  }

  public String gAddressLine()
  {
    return StringUtil.ensure(gAddress1(), "", " ") + StringUtil.ensure(gAddress2(), "", "");
  }

  public String gCityLine()
  {
    return StringUtil.ensure(gCity(), "", ", ") + StringUtil.ensure(gState(), "", ", ")
        + StringUtil.ensure(gZip());
  }

  public String gAddress1()
  {
    return gAddressPart(0);
  }

  public void setAddress1(String inAddress1)
  {
    setAddressPart(0, inAddress1);
  }

  public String gAddress2()
  {
    return gAddressPart(1);
  }

  public void setAddress2(String inAddress2)
  {
    setAddressPart(1, inAddress2);
  }

  public String gCity()
  {
    return gAddressPart(2);
  }

  public void setCity(String inCity)
  {
    setAddressPart(2, inCity);
  }

  public String gState()
  {
    return gAddressPart(3);
  }

  public void setState(String inState)
  {
    setAddressPart(3, inState);
  }

  public String gZip()
  {
    return gAddressPart(4);
  }

  public void setZip(String inZip)
  {
    setAddressPart(4, inZip);
  }

  private String gAddressPart(int inIndex)
  {
    return mAddress == null ? null : mAddress[inIndex];
  }

  private void setAddressPart(int inIndex, String inText)
  {
    if (mAddress == null)
    {
      mAddress = new String[5];
    }
    mAddress[inIndex] = inText;
  }

  @Override
  @JsonIgnore
  public String getName()
  {
    return gName();
  }

  public String gName()
  {
    return StringUtil.ensure(mLast, "", ", ") + StringUtil.ensure(mFirst)
        + StringUtil.ensure(mSuffix, " ");
  }

  public String gNameAge()
  {
    int age = gAge();
    return getName() + (age == 0 ? "" : " " + age);
  }

  public int gAge()
  {
    return TimeUtil.getAge(mBirth);
  }

  @Override
  public String getId()
  {
    return String.valueOf(mId);
  }

  @JsonIgnore
  public Long getIdLong()
  {
    return mId;
  }

  @Override
  @JsonIgnore
  public String getType()
  {
    return "PERSON";
  }

  @Override
  @JsonIgnore
  public String getValue()
  {
    return getName();
  }

  public String toQuery()
  {
    StringBuilder builder = new StringBuilder();
    builder.append(StringUtil.ensure(mLast));
    builder.append(" ");
    builder.append(StringUtil.ensure(mFirst));
    builder.append(" ");
    builder.append(StringUtil.ensure(mSuffix));
    builder.append(" ");
    builder.append(gAddress());
    builder.append(" ");
    builder.append(StringUtil.ensure(mHome));
    builder.append(" ");
    builder.append(StringUtil.ensure(mMobile));
    builder.append(" ");
    builder.append(StringUtil.ensure(mEmail));
    return builder.toString();
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Person [mId=");
    builder.append(mId);
    builder.append(", mLast=");
    builder.append(mLast);
    builder.append(", mFirst=");
    builder.append(mFirst);
    builder.append(", mSuffix=");
    builder.append(mSuffix);
    builder.append("]");
    return builder.toString();
  }
}