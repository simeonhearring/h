package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.SideBarPresenter;
import h.style.g.client.ui.AbstractView;

public class SideBarView extends AbstractView implements SideBarPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, SideBarView>
  {
  }

  SideBarPresenter mPresenter;

  public SideBarView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new SideBarPresenter(this);
  }
}