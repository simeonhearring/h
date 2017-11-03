package h.khall.client;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import com.google.gwt.core.client.Callback;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.ui.RootPanel;

import h.khall.client.ui.LoginView;
import h.khall.client.ui.MainView;
import h.style.g.client.AbstractEntryPoint;
import h.style.g.client.ui.event.LoadMainEvent;

public class KhallEntryPoint extends AbstractEntryPoint implements Callback<Void, Exception>
{
  @Override
  public void dispatch(LoadMainEvent inEvent)
  {
    new MainView();

    Element ele = Document.get().getElementsByTagName("head").getItem(0);
    ele.appendChild(scriptTag("js/khall.js"));

    // new PageView().attach(RootPanel.get());
    // new RegisterView().attach(RootPanel.get());
    new LoginView().attach(RootPanel.get());
    // Global.getInstance().fire(new LoginEvent());
  }

  private ScriptElement scriptTag(String inSrc)
  {
    ScriptElement sce = Document.get().createScriptElement();
    sce.setType("text/javascript");
    sce.setSrc(inSrc);
    return sce;
  }

  @Override
  public void onFailure(Exception inReason)
  {
    Notify.notify("Not Injected");
  }

  @Override
  public void onSuccess(Void inResult)
  {
    Notify.notify("Injected");
  }
}