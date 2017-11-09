package h.khall.client.model;

import com.google.gwt.user.client.ui.HasWidgets;

import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.ProfileEvent;
import h.khall.client.ui.event.ResendProfileEvent;
import h.style.g.client.model.Attach;

public class MainPresenter extends AbstractPresenter<MainPresenter.Display>
  implements AttachEvent.Handler, ResendProfileEvent.Handler
{
  public MainPresenter(MainPresenter.Display inDisplay)
  {
    super();
    initDisplay(inDisplay);
  }

  public void handlers()
  {
    addHandler(AttachEvent.TYPE, this);
    addHandler(ResendProfileEvent.TYPE, this);
  }

  @Override
  public void dispatch(AttachEvent inEvent)
  {
    switch (inEvent.getType())
    {
      case LOGIN:
        attach(mDisplay.login());
        break;
      case LOGOUT:
        attach(mDisplay.login());
        break;
      case FORGOT:
        attach(mDisplay.forgot());
        break;
      case MAILBOX:
        attach(mDisplay.pagefull());
        break;
      case REGISTER:
        attach(mDisplay.register());
        break;
      case MIDWEEK:
        attach(mDisplay.page());
        break;
      default:
        break;
    }
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