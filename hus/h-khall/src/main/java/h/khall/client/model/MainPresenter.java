package h.khall.client.model;

import com.google.gwt.user.client.ui.HasWidgets;

import h.khall.client.ui.event.LoginEvent;
import h.khall.client.ui.event.RegisterEvent;
import h.style.g.client.model.AbstractPresenter;

public class MainPresenter extends AbstractPresenter<MainPresenter.Display>
implements RegisterEvent.Handler, LoginEvent.Handler
{
  public MainPresenter(MainPresenter.Display inDisplay)
  {
    super();
    initDisplay(inDisplay);
  }

  public void events()
  {
    addHandler(RegisterEvent.TYPE, this);
    addHandler(LoginEvent.TYPE, this);
  }

  @Override
  public void dispatch(RegisterEvent inEvent)
  {
    attach(mDisplay.register());
  }

  @Override
  public void dispatch(LoginEvent inEvent)
  {
    attach(mDisplay.login());
  }

  private void attach(Attach inAttach)
  {
    inAttach.attach(mDisplay.root());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    HasWidgets root();

    LoginPresenter.Display login();

    RegisterPresenter.Display register();
  }
}