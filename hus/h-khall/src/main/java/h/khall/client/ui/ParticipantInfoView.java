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
}