package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.ui.event.PageAnalysisEvent;
import h.khall.client.ui.event.PageMinistryEvent;
import h.khall.client.ui.event.PageMissingEvent;
import h.khall.client.ui.event.ResendEvent;
import h.style.g.client.ui.AbstractView;
import h.style.g.client.ui.event.RefreshEvent;

public class MinistryNavView extends AbstractView
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, MinistryNavView>
  {
  }

  @UiField
  Anchor mA1, mA2, mA3, mA4, mA5;

  public MinistryNavView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  @UiHandler(
  {
      "mA1", "mA2", "mA3", "mA4", "mA5"
  })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mA1.equals(source))
    {
      fire(new PageMinistryEvent());
      fire(new ResendEvent(), new RefreshEvent());
    }
    else if (mA2.equals(source))
    {
      fire(new PageAnalysisEvent());
      fire(new ResendEvent(), new RefreshEvent());
    }
    else if (mA3.equals(source))
    {
      fire(new PageMissingEvent());
      fire(new ResendEvent(), new RefreshEvent());
    }
    else if (mA4.equals(source))
    {
    }
    else if (mA5.equals(source))
    {
    }
  }
}