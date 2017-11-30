package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.LoginPresenter;
import h.style.g.client.ui.util.StorageUtil;

public class LoginView extends AbstractView<LoginPresenter> implements LoginPresenter.Display
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

  public LoginView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new LoginPresenter(this);

    if (StorageUtil.hasEncrypt())
    {
      mEmail.setText(StorageUtil.getEncryptKey("email"));
      mCongNum.setText(StorageUtil.getEncryptKey("default"));
      mEncrypt.setValue(StorageUtil.getEncryptKey(null));
    }
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

  @UiHandler(
  {
      "mEmail", "mCongNum"
  })
  public void onChange(ChangeEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mEmail.equals(source))
    {
      addStorageValue("email", mEmail.getValue());
    }
    else if (mCongNum.equals(source))
    {
      addStorageValue("default", mCongNum.getValue());

      String key = StorageUtil.getEncryptKey(null);

      if (key != null)
      {
        mEncrypt.setValue(key);
      }
    }
  }
}