package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.EmptyPresenter;

public class PersonInfoView extends AbstractView<EmptyPresenter>
    implements EmptyPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonInfoView>
  {
  }

  public PersonInfoView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new EmptyPresenter(this);
  }
}