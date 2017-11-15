package h.khall.shared.model;

import java.util.Date;

@SuppressWarnings("serial")
public class Person extends h.model.shared.Person
{
  private String mFsg;
  private String mFaith;
  private Date mBaptized;
  private Roles mRoles;
  private Categories mCategories;
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
      mStudent.normalize();
    }
    if (mMinistry == null)
    {
      mMinistry = new Ministry();
      mMinistry.normalize();
    }
    if (mRoles == null)
    {
      mRoles = new Roles();
      mRoles.normalize();
    }
    if (mCategories == null)
    {
      mCategories = new Categories();
      mCategories.normalize();
    }
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

  public Roles getRoles()
  {
    return mRoles;
  }

  public void setRoles(Roles inRoles)
  {
    mRoles = inRoles;
  }

  public Categories getCategories()
  {
    return mCategories;
  }

  public void setCategories(Categories inCategories)
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

  public boolean isStudent()
  {
    return mRoles.contains(Roles.Role.STUDENT);
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