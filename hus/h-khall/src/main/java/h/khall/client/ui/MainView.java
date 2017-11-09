package h.khall.client.ui;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;

import h.khall.client.model.ForgotPresenter;
import h.khall.client.model.LoginPresenter;
import h.khall.client.model.MainPresenter;
import h.khall.client.model.PageFullPresenter;
import h.khall.client.model.PagePresenter;
import h.khall.client.model.RegisterPresenter;
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
  public LoginPresenter.Display login()
  {
    return new LoginView();
  }

  @Override
  public ForgotPresenter.Display forgot()
  {
    return new ForgotView();
  }

  @Override
  public RegisterPresenter.Display register()
  {
    return new RegisterView();
  }

  @Override
  public PagePresenter.Display page()
  {
    return new PageView();
  }

  @Override
  public PageFullPresenter.Display pagefull()
  {
    return new PageFullView();
  }
}