package h.model.shared.khall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import h.model.shared.khall.Categories.Category;
import h.model.shared.khall.Roles.Role;
import h.model.shared.util.EnumUtil;
import h.model.shared.util.TimeUtil;

@SuppressWarnings("serial")
public class Person extends h.model.shared.Person
{
  public enum Status
  {
    RE,
    IR,
    IA
  }

  private Integer mCongId;
  private Integer mFsgId;
  private Date mBaptized;
  private Roles mRoles;
  private Categories mCategories;
  private String mEmergency;
  private String mChildren;
  private Long mHead;
  private String mFamily;

  private Date mPublishing;

  private List<Part> mParts;
  private List<Hall> mHalls;
  private List<Comment> mComments;

  public String gLocater()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("c").append(mCongId).append(",");
    sb.append("f").append(mFsgId).append(",");
    return sb.toString();
  }

  public void normalize()
  {
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
    if (mParts == null)
    {
      mParts = new ArrayList<>();
    }
    if (mHalls == null)
    {
      mHalls = new ArrayList<>();
    }
  }

  @Override
  public String gNameAge()
  {
    int age = TimeUtil.getAge(mBaptized);
    return super.gNameAge() + (age == 0 ? "" : " / " + age);
  }

  public List<Part> getParts()
  {
    return mParts;
  }

  public void setParts(List<Part> inParts)
  {
    mParts = inParts;
  }

  public List<Hall> getHalls()
  {
    return mHalls;
  }

  public void setHalls(List<Hall> inHalls)
  {
    mHalls = inHalls;
  }

  @JsonIgnore
  public boolean isAvailable(Part inPart, Gender inGender)
  {
    return isMember() && mParts.contains(inPart) && (inGender == null || inGender.equals(getGender()));
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

  public String gRoles()
  {
    StringBuilder ret = new StringBuilder();
    for (Role value : mRoles.getRoles())
    {
      ret.append(value.gLabel()).append(" ");
    }
    return ret.toString();
  }

  public String gCategories()
  {
    StringBuilder ret = new StringBuilder();
    for (Category value : mCategories.getCategories())
    {
      ret.append(value.gLabel()).append(" ");
    }
    return ret.toString();
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

  public List<Comment> getComments()
  {
    return mComments;
  }

  public void setComments(List<Comment> inComments)
  {
    mComments = inComments;
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
  public boolean isServant()
  {
    return mRoles.contains(Roles.Role.MINISTERIAL_SERVANT);
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

  @JsonIgnore
  public boolean isPublisher()
  {
    return mRoles.contains(Roles.Role.PUBLISHER);
  }

  @JsonIgnore
  public boolean isOn15MinuteIncrement()
  {
    return mCategories.contains(Categories.Category.FIFTEEN_MINUTE_INCREMENT);
  }

  @JsonIgnore
  public boolean isInfirmRegularPioneer()
  {
    return mCategories.contains(Categories.Category.INFIRM_REGULAR_PIONEER);
  }

  @JsonIgnore
  public boolean isDeaf()
  {
    return mCategories.contains(Categories.Category.DEAF);
  }

  @JsonIgnore
  public boolean isBlind()
  {
    return mCategories.contains(Categories.Category.BLIND);
  }

  public Date getPublishing()
  {
    return mPublishing;
  }

  public void setPublishing(Date inPublishing)
  {
    mPublishing = inPublishing;
  }

  @Override
  public String toQuery()
  {
    StringBuilder builder = new StringBuilder();
    builder.append(super.toQuery());
    builder.append(" ");
    builder.append(getGender());
    builder.append(" ");
    builder.append(gRoles());
    builder.append(" ");
    builder.append(gCategories());
    return builder.toString();
  }

  public boolean isMember()
  {
    return mFsgId != null && !mFsgId.equals(0);
  }

  public boolean isBaptized()
  {
    return mBaptized != null;
  }

  public String gGenderName()
  {
    return EnumUtil.name(getGender());
  }

  public boolean isFsg(Integer inId)
  {
    return mFsgId.equals(inId);
  }

  public void moveout()
  {
    mRoles.getRoles().clear();
    mRoles.getRoles().add(Role.FAMILY);
  }
}