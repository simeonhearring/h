package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PageParticipantPresenter;

public class PageParticipantView extends AbstractView<PageParticipantPresenter> implements PageParticipantPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageParticipantView>
  {
  }

  public PageParticipantView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PageParticipantPresenter(this);
  }

  @Override
  protected void onLoad()
  {
    slimScroll();
  }
}