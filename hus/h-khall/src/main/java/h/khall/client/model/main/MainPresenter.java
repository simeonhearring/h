package h.khall.client.model.main;

import com.google.gwt.user.client.ui.HasWidgets;

import h.khall.client.model.AbstractPresenter;
import h.khall.client.model.pages.PageForgotPresenter;
import h.khall.client.model.pages.PageFullPresenter;
import h.khall.client.model.pages.PageLoginPresenter;
import h.khall.client.model.pages.PagePresenter;
import h.khall.client.model.pages.PageRegisterPresenter;
import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.ClientEvent;
import h.khall.client.ui.event.ProfileEvent;
import h.khall.client.ui.event.ResendEvent;
import h.style.g.client.model.Attach;

public class MainPresenter extends AbstractPresenter<MainPresenter.Display>
  implements AttachEvent.Handler, ResendEvent.Handler
{
  public MainPresenter(MainPresenter.Display inDisplay)
  {
    super();
    initDisplay(inDisplay);
  }

  public void handlers()
  {
    addHandler(AttachEvent.TYPE, this);
    addHandler(ResendEvent.TYPE, this);
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
      case KHALL:
        attach(mDisplay.khall());
        break;
      default:
        break;
    }
  }

  @Override
  public void dispatch(ResendEvent inEvent)
  {
    fire(new ProfileEvent(mProfile), new ClientEvent(mClient));
  }

  private void attach(Attach inAttach)
  {
    inAttach.attach(mDisplay.root());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    HasWidgets root();

    PageLoginPresenter.Display login();

    PageRegisterPresenter.Display register();

    PagePresenter.Display khall();

    PageFullPresenter.Display pagefull();

    PageForgotPresenter.Display forgot();
  }
}