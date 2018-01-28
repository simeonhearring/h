package h.khall.client.ui;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;

import h.khall.client.model.main.MainPresenter;
import h.khall.client.model.pages.PageForgotPresenter;
import h.khall.client.model.pages.PageFullPresenter;
import h.khall.client.model.pages.PageLoginPresenter;
import h.khall.client.model.pages.PagePresenter;
import h.khall.client.model.pages.PageRegisterPresenter;
import h.style.g.client.ui.AbstractView;

public class MainView extends AbstractView implements MainPresenter.Display
{
  public MainView()
  {
    new MainPresenter(this).handlers();
  }

  @Override
  public HasWidgets root()
  {
    return RootPanel.get();
  }

  @Override
  public PageLoginPresenter.Display login()
  {
    return new PageLoginView();
  }

  @Override
  public PageForgotPresenter.Display forgot()
  {
    return new PageForgotView();
  }

  @Override
  public PageRegisterPresenter.Display register()
  {
    return new PageRegisterView();
  }

  @Override
  public PagePresenter.Display khall()
  {
    return new PageKhallView();
  }

  @Override
  public PageFullPresenter.Display pagefull()
  {
    return new PageFullView();
  }
}