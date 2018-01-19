package h.khall.client.ui;

import java.util.List;

import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasText;

import h.khall.client.model.AbstractPresenter;

public class HasGLabelView<P extends AbstractPresenter<?>> extends AbstractView<P>
{
  @UiField
  Heading mLabel;

  @UiField
  HTMLPanel mOptions;

  public HasText getLabel()
  {
    return mLabel;
  }

  public String[] gOption(ClickEvent inEvent)
  {
    CheckBox check = (CheckBox) inEvent.getSource();
    return new String[]
    {
        check.getId(), String.valueOf(check.getValue())
    };
  }

  public void undo(ClickEvent inEvent)
  {
    CheckBox check = (CheckBox) inEvent.getSource();
    check.setValue(!check.getValue());
  }

  public void addOption(String inLabel, String inId, ClickHandler inHandler)
  {
    CheckBox check = new CheckBox(inLabel);
    check.setId(inId);
    check.addClickHandler(inHandler);
    mOptions.add(check);
  }

  public <T extends Enum<?>> void setOptions(List<T> inList)
  {
    check(keys(inList), mOptions);
  }
}