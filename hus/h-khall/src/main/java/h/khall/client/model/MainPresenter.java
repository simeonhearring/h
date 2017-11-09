package h.khall.client.model;

import com.google.gwt.user.client.ui.HasWidgets;

import h.khall.client.ui.event.ForgotEvent;
import h.khall.client.ui.event.LoginEvent;
import h.khall.client.ui.event.LogoutEvent;
import h.khall.client.ui.event.PageEvent;
import h.khall.client.ui.event.ProfileEvent;
import h.khall.client.ui.event.RegisterEvent;
import h.khall.client.ui.event.ResendProfileEvent;
import h.style.g.client.model.Attach;

public class MainPresenter extends AbstractPresenter<MainPresenter.Display>
implements LoginEvent.Handler, RegisterEvent.Handler, PageEvent.Handler, LogoutEvent.Handler,
  ResendProfileEvent.Handler, ForgotEvent.Handler
{
  public MainPresenter(MainPresenter.Display inDisplay)
  {
    super();
    initDisplay(inDisplay);
  }

  public void handlers()
  {
    addHandler(LoginEvent.TYPE, this);
    addHandler(RegisterEvent.TYPE, this);
    addHandler(PageEvent.TYPE, this);
    addHandler(LogoutEvent.TYPE, this);
    addHandler(ForgotEvent.TYPE, this);
    addHandler(ResendProfileEvent.TYPE, this);
  }

  @Override
  public void dispatch(PageEvent inEvent)
  {
    attach(mDisplay.page());
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

  @Override
  public void dispatch(LogoutEvent inEvent)
  {
    attach(mDisplay.login());
  }

  @Override
  public void dispatch(ForgotEvent inEvent)
  {
    attach(mDisplay.forgot());
  }

  @Override
  public void dispatch(ResendProfileEvent inEvent)
  {
    fire(new ProfileEvent(mProfile));
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

    PagePresenter.Display page();

    PageFullPresenter.Display pagefull();

    ForgotPresenter.Display forgot();
  }
}