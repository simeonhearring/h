package h.khall.client.ui;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.ListItem;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PersonPresenter;
import h.model.shared.Person.Gender;
import h.style.g.client.ui.AbstractView;
import h.style.g.client.ui.InputView;

public class PersonView extends AbstractView implements PersonPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonView>
  {
  }

  @UiField
  ListItem mStep1l, mStep2l, mStep3l;

  @UiField
  Anchor mStep1a, mStep2a, mStep3a;

  @UiField
  HTMLPanel mStep1c, mStep2c, mStep3c, mDate;

  @UiField
  InputView mFirst, mMiddle, mLast, mSuffix, mEmail, mMobile, mHome;

  @UiField
  InputView mGender, mBirth;

  @UiField
  InputView mAddress1, mAddress2, mCity, mState, mZip;

  @UiField
  InputView mFsg, mFaith;

  @UiField
  InputView mBaptized, mPublishing;

  @UiField
  InputView mEmergency, mChildren;

  @UiField
  InputView mHead, mFamily, mCategories, mRoles;

  @UiField
  Anchor mSave;

  PersonPresenter mPresenter;

  public PersonView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PersonPresenter(this);
    setElementId(mDate.getElement());
  }

  @UiHandler(
      {
        "mStep1a", "mStep2a", "mStep3a"
      })
  public void wonClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mStep1a.equals(source))
    {
      clickStep1();
    }
    else if (mStep2a.equals(source))
    {
      clickStep2();
    }
    else if (mStep3a.equals(source))
    {
      clickStep3();
    }
  }

  @UiHandler(
      {
        "mSave"
      })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mSave.equals(source))
    {
      mPresenter.save();
    }
  }

  @UiHandler(
      {
        "mFirst",
        "mMiddle",
        "mLast",
        "mSuffix",
        "mGender",
        "mBirth",
        "mEmail",
        "mMobile",
        "mHome",
        "mAddress1",
        "mAddress2",
        "mCity",
        "mState",
        "mZip",
        "mFsg",
        "mFaith",
        "mBaptized",
        "mPublishing",
        "mRoles",
        "mCategories",
        "mEmergency",
        "mChildren",
        "mHead",
        "mFamily",
      })
  public void onChange(ValueChangeEvent<String> inEvent)
  {
    Object source = inEvent.getSource();
    if (mFirst.equals(source))
    {
      mPresenter.chgFirst(mFirst.getValue());
    }
    else if (mMiddle.equals(source))
    {
      mPresenter.chgMiddle(mMiddle.getValue());
    }
    else if (mLast.equals(source))
    {
      mPresenter.chgLast(mLast.getValue());
    }
    else if (mSuffix.equals(source))
    {
      mPresenter.chgSuffix(mSuffix.getValue());
    }
    // else if (mGender.equals(source))
    // {
    // mPresenter.chgGender(mGender.getValue());
    // }
    // else if (mBirth.equals(source))
    // {
    // mPresenter.chgBirth(mBirth.getValue());
    // }
    else if (mEmail.equals(source))
    {
      mPresenter.chgEmail(mEmail.getValue());
    }
    else if (mMobile.equals(source))
    {
      mPresenter.chgMobile(mMobile.getValue());
    }
    else if (mHome.equals(source))
    {
      mPresenter.chgHome(mHome.getValue());
    }
    else if (mAddress1.equals(source))
    {
      mPresenter.chgAddress1(mAddress1.getValue());
    }
    else if (mAddress2.equals(source))
    {
      mPresenter.chgAddress2(mAddress2.getValue());
    }
    else if (mCity.equals(source))
    {
      mPresenter.chgCity(mCity.getValue());
    }
    else if (mState.equals(source))
    {
      mPresenter.chgState(mState.getValue());
    }
    else if (mZip.equals(source))
    {
      mPresenter.chgZip(mZip.getValue());
    }
    else if (mFsg.equals(source))
    {
      mPresenter.chgFsg(mFsg.getValue());
    }
    else if (mFaith.equals(source))
    {
      mPresenter.chgFaith(mFaith.getValue());
    }
    // else if (mBaptized.equals(source))
    // {
    // mPresenter.chgBaptized(mBaptized.getValue());
    // }
    // else if (mPublishing.equals(source))
    // {
    // mPresenter.chgPublishing(mPublishing.getValue());
    // }
    else if (mRoles.equals(source))
    {
      mPresenter.chgTypes(mRoles.getValue());
    }
    else if (mCategories.equals(source))
    {
      mPresenter.chgCategories(mCategories.getValue());
    }
    else if (mEmergency.equals(source))
    {
      mPresenter.chgEmergency(mEmergency.getValue());
    }
    else if (mChildren.equals(source))
    {
      mPresenter.chgChildren(mChildren.getValue());
    }
    else if (mHead.equals(source))
    {
      mPresenter.chgHead(mHead.getValue());
    }
    else if (mFamily.equals(source))
    {
      mPresenter.chgFamily(mFamily.getValue());
    }
  }

  @Override
  protected void onLoad()
  {
    icheck();
    steps();
    datepicker();
    datepicker(mDate.getId());
  }

  @Override
  public void setFirst(String inValue)
  {
    mFirst.setValue(inValue);
  }

  @Override
  public void setMiddle(String inValue)
  {
    mMiddle.setValue(inValue);
  }

  @Override
  public void setLast(String inValue)
  {
    mLast.setValue(inValue);
  }

  @Override
  public void setSuffix(String inValue)
  {
    mSuffix.setValue(inValue);
  }

  @Override
  public void setGender(Gender inValue)
  {
    // mGender.setValue(inValue);
  }

  @Override
  public void setBirth(Date inValue)
  {
    // mBirth.setValue(inValue);
  }

  @Override
  public void setEmail(String inValue)
  {
    mEmail.setValue(inValue);
  }

  @Override
  public void setMobile(String inValue)
  {
    mMobile.setValue(inValue);
  }

  @Override
  public void setHome(String inValue)
  {
    mHome.setValue(inValue);
  }

  @Override
  public void setAddress1(String inValue)
  {
    mAddress1.setValue(inValue);
  }

  @Override
  public void setAddress2(String inValue)
  {
    mAddress2.setValue(inValue);
  }

  @Override
  public void setCity(String inValue)
  {
    mCity.setValue(inValue);
  }

  @Override
  public void setState(String inValue)
  {
    mState.setValue(inValue);
  }

  @Override
  public void setZip(String inValue)
  {
    mZip.setValue(inValue);
  }

  @Override
  public void setFsg(String inValue)
  {
    mFsg.setValue(inValue);
  }

  @Override
  public void setFaith(String inValue)
  {
    mFaith.setValue(inValue);
  }

  @Override
  public void setBaptized(Date inValue)
  {
    // mBaptized.setValue(inValue);
  }

  @Override
  public void setPublishing(Date inValue)
  {
    // mPublishing.setValue(inValue);
  }

  @Override
  public void setRoles(String inValue)
  {
    mRoles.setValue(inValue);
  }

  @Override
  public void setCategories(String inValue)
  {
    mCategories.setValue(inValue);
  }

  @Override
  public void setEmergency(String inValue)
  {
    mEmergency.setValue(inValue);
  }

  @Override
  public void setChildren(String inValue)
  {
    mChildren.setValue(inValue);
  }

  @Override
  public void setHead(String inValue)
  {
    mHead.setValue(inValue);
  }

  @Override
  public void setFamily(String inValue)
  {
    mFamily.setValue(inValue);
  }

  @Override
  public void reset()
  {
    clickStep1();
    setFirst(null);
    setMiddle(null);
    setSuffix(null);
    setGender(null);
    setEmail(null);
    setMobile(null);
    setAddress1(null);
    setAddress2(null);
    setCity(null);
    setState(null);
    setFsg(null);
    setFaith(null);
    setBaptized(null);
    setPublishing(null);
    setRoles(null);
    setCategories(null);
    setEmergency(null);
    setChildren(null);
    setHead(null);
    setFamily(null);
  }

  private void clickStep3()
  {
    mStep1l.setStyleName("done");
    mStep2l.setStyleName("done");
    mStep3l.setStyleName("current");

    mStep1c.setVisible(false);
    mStep2c.setVisible(false);
    mStep3c.setVisible(true);
  }

  private void clickStep2()
  {
    mStep1l.setStyleName("done");
    mStep2l.setStyleName("current");
    mStep3l.setStyleName("disabled");

    mStep1c.setVisible(false);
    mStep2c.setVisible(true);
    mStep3c.setVisible(false);
  }

  private void clickStep1()
  {
    mStep1l.setStyleName("current");
    mStep2l.setStyleName("disabled");
    mStep3l.setStyleName("disabled");

    mStep1c.setVisible(true);
    mStep2c.setVisible(false);
    mStep3c.setVisible(false);
  }
}