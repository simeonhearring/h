package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Label;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PersonInfoPresenter;

public class PersonInfoView extends AbstractView<PersonInfoPresenter>
  implements PersonInfoPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonInfoView>
  {
  }

  @UiField
  Label mName;

  @UiField
  RoleView mRole;

  @UiField
  CategoryView mCategory;

  @UiField
  PartView mPart;

  @UiField
  HallView mHall;

  @UiField
  FsgView mFsg;

  @UiField
  HeadOfHouseView mHead;

  public PersonInfoView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PersonInfoPresenter(this).handlers();
  }

  @Override
  public void setName(String inName)
  {
    mName.setText(inName);
  }

  @Override
  public void setRoleVisible(boolean inVisible)
  {
    mRole.setVisible(inVisible);
  }

  @Override
  public void setCategoryVisible(boolean inVisible)
  {
    mCategory.setVisible(inVisible);
  }

  @Override
  public void setPartVisible(boolean inVisible)
  {
    mPart.setVisible(inVisible);
  }

  @Override
  public void setHallVisible(boolean inVisible)
  {
    mHall.setVisible(inVisible);
  }

  @Override
  public void setFsgVisible(boolean inVisible)
  {
    mFsg.setVisible(inVisible);
  }

  @Override
  public void setHohVisible(boolean inVisible)
  {
    mHead.setVisible(inVisible);
  }
}