package h.khall.client.ui;

import com.google.gwt.core.client.GWT;

import h.khall.client.model.AbstractPresenter;
import h.khall.client.resource.Messages;

public class AbstractView<P extends AbstractPresenter<?>>
  extends h.style.g.client.ui.AbstractView
{
  protected static final Messages MESSAGE = GWT.create(Messages.class);

  protected P mPresenter;

  @Override
  protected void onUnload()
  {
    super.onUnload();
    mPresenter.removeHandlers();
  }
}