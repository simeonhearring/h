package h.khall.shared.model;

import java.util.Date;

@SuppressWarnings("serial")
public class Person extends h.model.shared.Person
{
  public enum Type
  {
    STUDENT,
    MINISTRY;
  }

  private String mFsg;
  private String mFaith;
  private Date mBaptized;
  private String mRoles;
  private String mCategories;
  private String mEmergency;
  private String mChildren;
  private String mHead;
  private String mFamily;

  private Student mStudent;
  private Ministry mMinistry;

  public void normalize()
  {
    if (mStudent == null)
    {
      mStudent = new Student();
    }
    if (mMinistry == null)
    {
      mMinistry = new Ministry();
    }
  }

  public String getStatus(Type inType)
  {
    String ret = null;

    switch (inType)
    {
      case MINISTRY:
        ret = getMinistryStatus();
        break;
      case STUDENT:
        ret = getStudentStatus();
        break;
      default:
        break;
    }

    return ret;
  }

  public String getFsg()
  {
    return mFsg;
  }

  public void setFsg(String inFsg)
  {
    mFsg = inFsg;
  }

  public String getFaith()
  {
    return mFaith;
  }

  public void setFaith(String inFaith)
  {
    mFaith = inFaith;
  }

  public Date getBaptized()
  {
    return mBaptized;
  }

  public void setBaptized(Date inBaptized)
  {
    mBaptized = inBaptized;
  }

  public String getRoles()
  {
    return mRoles;
  }

  public void setRoles(String inTypes)
  {
    mRoles = inTypes;
  }

  public String getCategories()
  {
    return mCategories;
  }

  public void setCategories(String inCategories)
  {
    mCategories = inCategories;
  }

  public String getEmergency()
  {
    return mEmergency;
  }

  public void setEmergency(String inEmergency)
  {
    mEmergency = inEmergency;
  }

  public String getChildren()
  {
    return mChildren;
  }

  public void setChildren(String inChildren)
  {
    mChildren = inChildren;
  }

  public String getHead()
  {
    return mHead;
  }

  public void setHead(String inHead)
  {
    mHead = inHead;
  }

  public String getFamily()
  {
    return mFamily;
  }

  public void setFamily(String inFamily)
  {
    mFamily = inFamily;
  }

  public String getStudentStatus()
  {
    return mStudent.getStatus();
  }

  public Student getStudent()
  {
    return mStudent;
  }

  public void setStudent(Student inStudent)
  {
    mStudent = inStudent;
  }

  public String getMinistryStatus()
  {
    return mMinistry.getStatus();
  }

  public Ministry getMinistry()
  {
    return mMinistry;
  }

  public void setMinistry(Ministry inMinistry)
  {
    mMinistry = inMinistry;
  }

  public Date getPublishing()
  {
    return mMinistry.getPublishing();
  }

  public void setPublishing(Date inValue)
  {
    mMinistry.setPublishing(inValue);
  }
}