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
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.RegisterPresenter;

public class RegisterView extends AbstractView<RegisterPresenter>
  implements RegisterPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, RegisterView>
  {
  }

  @UiField
  Input mFirst, mLast, mEmail, mCongNme, mCongNum;

  @UiField
  Button mRegister;

  @UiField
  Anchor mLogin;

  public RegisterView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new RegisterPresenter(this);
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
      "mLogin", "mRegister"
  })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mRegister.equals(source))
    {
      mPresenter.register(mCongNme.getText(), mCongNum.getText(), mEmail.getText(), mLast.getText(),
          mFirst.getText());
    }
    else if (mLogin.equals(source))
    {
      mPresenter.login();
    }
  }

  @Override
  protected void onLoad()
  {
    icheck();
  }
}