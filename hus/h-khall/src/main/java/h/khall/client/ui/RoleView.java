package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.HasGLabelPresenter;
import h.khall.client.model.RolePresenter;
import h.model.shared.khall.Roles.Role;

public class RoleView extends HasGLabelView<HasGLabelPresenter<Role>>
  implements RolePresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, RoleView>
  {
  }

  public RoleView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new RolePresenter(this).handlers();
  }
}