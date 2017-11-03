package h.khall.client;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import com.google.gwt.core.client.Callback;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.ui.RootPanel;

import h.khall.client.ui.MainView;
import h.khall.client.ui.PageView;
import h.style.g.client.AbstractEntryPoint;
import h.style.g.client.ui.event.LoadMainEvent;

public class KhallEntryPoint extends AbstractEntryPoint implements Callback<Void, Exception>
{
  @Override
  public void dispatch(LoadMainEvent inEvent)
  {
    new MainView();

    new PageView().attach(RootPanel.get());
    // new RegisterView().attach(RootPanel.get());
    // new LoginView().attach(RootPanel.get());
    // Global.getInstance().fire(new LoginEvent());
    // ScriptInjector.fromUrl("js/khall.js").setCallback(this);
    // ScriptInjector.fromUrl("js/plugins/metisMenu/jquery.metisMenu.js").setCallback(this);
    // ScriptInjector.fromUrl("js/plugins/slimscroll/jquery.slimscroll.min.js").setCallback(this);
    // ScriptInjector.fromUrl("js/inspinia.js").setCallback(this);
    // ScriptInjector.fromUrl("js/plugins/pace/pace.min.js").setCallback(this);

    Element head = Document.get().getElementsByTagName("head").getItem(0);
    head.appendChild(scriptTag("js/khall.js"));
    head.appendChild(scriptTag("js/plugins/metisMenu/jquery.metisMenu.js"));
    head.appendChild(scriptTag("js/plugins/slimscroll/jquery.slimscroll.min.js"));
    head.appendChild(scriptTag("js/inspinia.js"));
    head.appendChild(scriptTag("js/plugins/pace/pace.min.js"));
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