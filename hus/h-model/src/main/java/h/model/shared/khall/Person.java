package h.model.shared.khall;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import h.model.shared.khall.Roles.Role;

@SuppressWarnings("serial")
public class Person extends h.model.shared.Person
{
  private Integer mCongId;
  private Integer mFsgId;
  private Date mBaptized;
  private Roles mRoles;
  private Categories mCategories;
  private String mEmergency;
  private String mChildren;
  private Long mHead;
  private String mFamily;

  private Student mStudent;
  private Ministry mMinistry;

  public String gLocater()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("c").append(mCongId).append(",");
    sb.append("f").append(mFsgId).append(",");
    return sb.toString();
  }

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

  @JsonIgnore
  public boolean isAvailable(Part inPart)
  {
    return mStudent.getParts().contains(inPart);
  }

  public Integer getFsgId()
  {
    return mFsgId;
  }

  public void setFsgId(Integer inFsg)
  {
    mFsgId = inFsg;
  }

  public Integer getCongId()
  {
    return mCongId;
  }

  public void setCongId(Integer inCongId)
  {
    mCongId = inCongId;
  }

  public Date getBaptized()
  {
    return mBaptized;
  }

  public void setBaptized(Date inBaptized)
  {
    mBaptized = inBaptized;
  }

  @JsonIgnore
  public Role getRole()
  {
    return mRoles.gPrimary();
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

  public Long getHead()
  {
    return mHead;
  }

  public void setHead(Long inHead)
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

  @JsonIgnore
  public boolean isStudent()
  {
    return mRoles.contains(Roles.Role.STUDENT);
  }

  @JsonIgnore
  public boolean isElder()
  {
    return mRoles.contains(Roles.Role.ELDER);
  }

  @JsonIgnore
  public boolean isRegular()
  {
    return mRoles.contains(Roles.Role.REGULAR_PIONEER);
  }

  @JsonIgnore
  public boolean isAnoited()
  {
    return mRoles.contains(Roles.Role.ANOITED);
  }

  public Student getStudent()
  {
    return mStudent;
  }

  public void setStudent(Student inStudent)
  {
    mStudent = inStudent;
  }

  public Ministry getMinistry()
  {
    return mMinistry;
  }

  public void setMinistry(Ministry inMinistry)
  {
    mMinistry = inMinistry;
  }

  @JsonIgnore
  public Date getPublishing()
  {
    return mMinistry.getPublishing();
  }

  public void setPublishing(Date inValue)
  {
    mMinistry.setPublishing(inValue);
  }

  @Override
  public String toQuery()
  {
    StringBuilder builder = new StringBuilder();
    builder.append(super.toQuery());
    builder.append(" ");
    builder.append(getRole()); // expand and include category
    return builder.toString();
  }
}