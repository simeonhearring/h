package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.RegisterPresenter;
import h.style.g.client.ui.AbstractView;

public class RegisterView extends AbstractView
implements RegisterPresenter.Display, ScheduledCommand
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

  RegisterPresenter mPresenter;

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
  public void login()
  {
    new LoginView().attach(RootPanel.get());
  }

  @Override
  protected void onLoad()
  {
    super.onLoad();
    script();
  }

  public void script()
  {
    StringBuilder sb = new StringBuilder();

    sb.append("$(document).ready(function(){\n");
    sb.append("  $('.i-checks').iCheck({\n");
    sb.append("    checkboxClass: 'icheckbox_square-green',\n");
    sb.append("    radioClass: 'iradio_square-green',\n");
    sb.append("  });\n");
    sb.append("});");

    Element ele = Document.get().getElementsByTagName("body").getItem(0);

    ele.appendChild(scriptSrc("js/jquery-3.1.1.min.js"));
    ele.appendChild(scriptSrc("js/bootstrap.min.js"));
    ele.appendChild(scriptSrc("js/plugins/iCheck/icheck.min.js"));
    // head.appendChild(scriptText(sb.toString()));
    // calljs();
  }

  // TODO not being called
  private static native void calljs()
  /*-{
		$wnd.readyicheck();
  }-*/;

  private static native void setup()
  /*-{
		$wnd.$(document).ready(function() {
			$wnd.$('.i-checks').iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
			});
		});
  }-*/;

  @Override
  public void execute()
  {
    // script();
  }
}