package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.HallPresenter;
import h.khall.client.model.HasGLabelPresenter;
import h.model.shared.khall.Hall;

public class HallView extends HasGLabelView<HasGLabelPresenter<Hall>>
  implements HallPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, HallView>
  {
  }

  public HallView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new HallPresenter(this).handlers();
  }
}