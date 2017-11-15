package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.ParticipantPresenter;

public class ParticipantView extends AbstractView<ParticipantPresenter> implements ParticipantPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ParticipantView>
  {
  }

  public ParticipantView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new ParticipantPresenter(this);
  }

  @Override
  protected void onLoad()
  {
    slimScroll();
  }
}