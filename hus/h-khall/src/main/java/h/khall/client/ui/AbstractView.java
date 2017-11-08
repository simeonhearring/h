package h.khall.client.ui;

import h.khall.client.model.AbstractPresenter;

public class AbstractView<P extends AbstractPresenter<?>>
  extends h.style.g.client.ui.AbstractView
{
  protected P mPresenter;

  @Override
  protected void onUnload()
  {
    mPresenter.removeHandlers();
  }
}