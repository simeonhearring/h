package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class ContenteditableView extends AbstractView
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ContenteditableView>
  {
  }

  @UiField
  FlowPanel mBox;

  public ContenteditableView()
  {
    initWidget(BINDER.createAndBindUi(this));

    mBox.getElement().setAttribute("contenteditable", "true");
    mBox.getElement().setInnerHTML("hello<br></br>");

    mBox.getElement().getInnerText();
  }

}