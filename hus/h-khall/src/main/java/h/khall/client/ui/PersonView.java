package h.khall.client.ui;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.Input;
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
  HTMLPanel mStep1c, mStep2c, mStep3c;

  @UiField
  InputView mFirst, mMiddle, mLast, mSuffix, mEmail, mMobile, mHome;

  @UiField
  CheckBox mGender;

  @UiField
  Input mBirth, mBaptized, mPublishing;

  @UiField
  InputView mAddress1, mAddress2, mCity, mState, mZip;

  @UiField
  InputView mFsg;

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
        "mEmail",
        "mMobile",
        "mHome",
        "mAddress1",
        "mAddress2",
        "mCity",
        "mState",
        "mZip",
        "mFsg",
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
    if (mFirst.isSource(inEvent))
    {
      mPresenter.chgFirst(mFirst.getValue());
    }
    else if (mMiddle.isSource(inEvent))
    {
      mPresenter.chgMiddle(mMiddle.getValue());
    }
    else if (mLast.isSource(inEvent))
    {
      mPresenter.chgLast(mLast.getValue());
    }
    else if (mSuffix.isSource(inEvent))
    {
      mPresenter.chgSuffix(mSuffix.getValue());
    }
    // else if (mGender.isSource(inEvent))
    // {
    // mPresenter.chgGender(mGender.getValue());
    // }
    // else if (mBirth.isSource(inEvent))
    // {
    // mPresenter.chgBirth(mBirth.getValue());
    // }
    else if (mEmail.isSource(inEvent))
    {
      mPresenter.chgEmail(mEmail.getValue());
    }
    else if (mMobile.isSource(inEvent))
    {
      mPresenter.chgMobile(mMobile.getValue());
    }
    else if (mHome.isSource(inEvent))
    {
      mPresenter.chgHome(mHome.getValue());
    }
    else if (mAddress1.isSource(inEvent))
    {
      mPresenter.chgAddress1(mAddress1.getValue());
    }
    else if (mAddress2.isSource(inEvent))
    {
      mPresenter.chgAddress2(mAddress2.getValue());
    }
    else if (mCity.isSource(inEvent))
    {
      mPresenter.chgCity(mCity.getValue());
    }
    else if (mState.isSource(inEvent))
    {
      mPresenter.chgState(mState.getValue());
    }
    else if (mZip.isSource(inEvent))
    {
      mPresenter.chgZip(mZip.getValue());
    }
    else if (mFsg.isSource(inEvent))
    {
      mPresenter.chgFsg(mFsg.getValue());
    }
    // else if (mBaptized.isSource(inEvent))
    // {
    // mPresenter.chgBaptized(mBaptized.getValue());
    // }
    // else if (mPublishing.isSource(inEvent))
    // {
    // mPresenter.chgPublishing(mPublishing.getValue());
    // }
    else if (mRoles.isSource(inEvent))
    {
      mPresenter.chgTypes(mRoles.getValue());
    }
    else if (mCategories.isSource(inEvent))
    {
      mPresenter.chgCategories(mCategories.getValue());
    }
    else if (mEmergency.isSource(inEvent))
    {
      mPresenter.chgEmergency(mEmergency.getValue());
    }
    else if (mChildren.isSource(inEvent))
    {
      mPresenter.chgChildren(mChildren.getValue());
    }
    else if (mHead.isSource(inEvent))
    {
      mPresenter.chgHead(mHead.getValue());
    }
    else if (mFamily.isSource(inEvent))
    {
      mPresenter.chgFamily(mFamily.getValue());
    }
  }

  @Override
  protected void onLoad()
  {
    icheck();
    steps();
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