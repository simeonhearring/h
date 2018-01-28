package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.pages.PageSamplePresenter;

public class PageSampleView extends AbstractView<PageSamplePresenter> implements PageSamplePresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageSampleView>
  {
  }

  public PageSampleView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PageSamplePresenter(this);
  }
}