package h.model.shared;

import java.io.Serializable;
import java.util.Date;

import h.model.shared.util.StringUtil;

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

  private String mAddress1;
  private String mAddress2;
  private String mCity;
  private String mState;
  private String mZip;

  public Person()
  {
  }

  public Person(String inLast, String inFirst)
  {
    mLast = inLast;
    mFirst = inFirst;
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

  public String getAddress1()
  {
    return mAddress1;
  }

  public void setAddress1(String inAddress1)
  {
    mAddress1 = inAddress1;
  }

  public String getAddress2()
  {
    return mAddress2;
  }

  public void setAddress2(String inAddress2)
  {
    mAddress2 = inAddress2;
  }

  public String getCity()
  {
    return mCity;
  }

  public void setCity(String inCity)
  {
    mCity = inCity;
  }

  public String getState()
  {
    return mState;
  }

  public void setState(String inState)
  {
    mState = inState;
  }

  public String getZip()
  {
    return mZip;
  }

  public void setZip(String inZip)
  {
    mZip = inZip;
  }

  @Override
  public String getName()
  {
    return StringUtil.ensure(mLast, "", ", ") + StringUtil.ensure(mFirst)
        + StringUtil.ensure(mSuffix, " ");
  }

  @Override
  public String getId()
  {
    return String.valueOf(mId);
  }

  @Override
  public String getType()
  {
    return "PERSON";
  }

  @Override
  public String getValue()
  {
    return getName();
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