package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Label;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.ParticipantInfoPresenter;

public class ParticipantInfoView extends AbstractView<ParticipantInfoPresenter>
  implements ParticipantInfoPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ParticipantInfoView>
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
  PartView mHall;

  public ParticipantInfoView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new ParticipantInfoPresenter(this).handlers();
  }

  @Override
  public void setName(String inName)
  {
    mName.setText(inName);
  }

  @Override
  public void showRole(boolean inVisible)
  {
    mRole.setVisible(inVisible);
  }

  @Override
  public void showCategory(boolean inVisible)
  {
    mCategory.setVisible(inVisible);
  }

  @Override
  public void showPart(boolean inVisible)
  {
    mPart.setVisible(inVisible);
  }

  @Override
  public void showHall(boolean inVisible)
  {
    mHall.setVisible(inVisible);
  }
}