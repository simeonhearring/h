package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.HasGLabelPresenter;
import h.khall.client.model.PartPresenter;
import h.model.shared.khall.Part;

public class PartView extends HasGLabelView<HasGLabelPresenter<Part>>
  implements PartPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PartView>
  {
  }

  public PartView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PartPresenter(this).handlers();
  }
}