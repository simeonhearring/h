package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.LoginPresenter;
import h.style.g.client.ui.AbstractView;

public class LoginView extends AbstractView implements LoginPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, LoginView>
  {
  }

  @UiField
  Input mEmail, mPassword, mCongNum, mEncrypt;

  @UiField
  Button mLogin;

  @UiField
  Anchor mForgot, mCreate;

  LoginPresenter mPresenter;

  public LoginView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new LoginPresenter(this);
  }

  @Override
  public void attach(HasWidgets inPanel)
  {
    inPanel.clear();
    inPanel.add(this);
    ((UIObject) inPanel).addStyleName("gray-bg");
  }

  @UiHandler(
      {
        "mLogin", "mForgot", "mCreate"
      })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mLogin.equals(source))
    {
      mPresenter.login(mEmail.getText(), mPassword.getText(), mCongNum.getText(),
          mEncrypt.getText());
    }
    else if (mForgot.equals(source))
    {
      mPresenter.forgotPassword();
    }
    else if (mCreate.equals(source))
    {
      mPresenter.register();
    }
  }

  @Override
  public void loggedIn()
  {
    new PageView().attach(RootPanel.get());
  }

  @Override
  public void register()
  {
    new RegisterView().attach(RootPanel.get());
  }
}