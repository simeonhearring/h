package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.AddressPresenter;
import h.model.shared.khall.Person;

public class AddressView extends AbstractView<AddressPresenter> implements AddressPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, AddressView>
  {
  }

  @UiField
  Input mFirst, mMiddle, mLast, mSuffix;

  @UiField
  Input mAddress1, mAddress2, mCity, mState, mZip;

  @UiField
  Input mHome, mMobile, mEmail;

  @UiField
  Input mEmergency, mChildren;

  private long mId;

  public AddressView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new AddressPresenter(this).handlers();
  }

  @Override
  public void setPerson(Person inPerson)
  {
    mId = inPerson.getIdLong();

    mFirst.setText(inPerson.getFirst());
    mLast.setText(inPerson.getLast());
    mMiddle.setText(inPerson.getMiddle());
    mSuffix.setText(inPerson.getSuffix());

    mAddress1.setText(inPerson.gAddress1());
    mAddress2.setText(inPerson.gAddress2());
    mCity.setText(inPerson.gCity());
    mState.setText(inPerson.gState());
    mZip.setText(inPerson.gZip());

    mHome.setText(inPerson.getHome());
    mMobile.setText(inPerson.getMobile());

    mEmail.setText(inPerson.getEmail());

    mEmergency.setText(inPerson.getEmergency());
    mChildren.setText(inPerson.getChildren());
 }

  @Override
  public void editDetail(boolean inEnable)
  {
    mFirst.setEnabled(inEnable);
    mLast.setEnabled(inEnable);
    mMiddle.setEnabled(inEnable);
    mSuffix.setEnabled(inEnable);

    mAddress1.setEnabled(inEnable);
    mAddress2.setEnabled(inEnable);
    mCity.setEnabled(inEnable);
    mState.setEnabled(inEnable);
    mZip.setEnabled(inEnable);

    mEmergency.setEnabled(inEnable);
    mChildren.setEnabled(inEnable);
  }

  @Override
  public void editContactInfo(boolean inEnable)
  {
    mHome.setEnabled(inEnable);
    mMobile.setEnabled(inEnable);
    mEmail.setEnabled(inEnable);
  }

  @UiHandler(
  {
      "mFirst",
      "mMiddle",
      "mLast",
      "mSuffix",
      "mAddress1",
      "mAddress2",
      "mCity",
      "mState",
      "mZip",
      "mHome",
      "mMobile",
      "mEmail",
      "mEmergency",
      "mChildren"
  })
  public void onValueChange(ValueChangeEvent<String> inEvent)
  {
    Object source = inEvent.getSource();
    if (mAddress1.equals(source))
    {
      mPresenter.chgAddress1(mId, mAddress1.getText());
    }
    else if (mAddress2.equals(source))
    {
      mPresenter.chgAddress2(mId, mAddress2.getText());
    }
    else if (mCity.equals(source))
    {
      mPresenter.chgCity(mId, mCity.getText());
    }
    else if (mState.equals(source))
    {
      mPresenter.chgState(mId, mState.getText());
    }
    else if (mZip.equals(source))
    {
      mPresenter.chgZip(mId, mZip.getText());
    }
    else if (mHome.equals(source))
    {
      mPresenter.chgHome(mId, mHome.getText());
    }
    else if (mMobile.equals(source))
    {
      mPresenter.chgMobile(mId, mMobile.getText());
    }
    else if (mEmail.equals(source))
    {
      mPresenter.chgEmail(mId, mEmail.getText());
    }
    else if (mFirst.equals(source))
    {
      mPresenter.chgFirst(mId, mFirst.getText());
    }
    else if (mLast.equals(source))
    {
      mPresenter.chgLast(mId, mLast.getText());
    }
    else if (mMiddle.equals(source))
    {
      mPresenter.chgMiddle(mId, mMiddle.getText());
    }
    else if (mSuffix.equals(source))
    {
      mPresenter.chgSuffix(mId, mSuffix.getText());
    }
    else if (mEmergency.equals(source))
    {
      mPresenter.chgEmergency(mId, mEmergency.getText());
    }
    else if (mChildren.equals(source))
    {
      mPresenter.chgChildren(mId, mChildren.getText());
    }
  }
}