package h.style.g.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

public class AbstractLabel extends Composite
{
  protected Label mLabel;

  public void setLabel(Label inLabel)
  {
    mLabel = inLabel;
  }

  public String getValue()
  {
    return mLabel.getText();
  }

  public void setValue(String inValue)
  {
    mLabel.setText(inValue);
  }

  public void setHTML(String inHtml)
  {
    if (mLabel instanceof HTML)
    {
      ((HTML) mLabel).setHTML(inHtml);
    }
  }

  public void setText(String inValue)
  {
    setValue(inValue);
  }

  @Override
  public void setStyleName(String inStyle)
  {
    mLabel.setStyleName(inStyle);
  }

  @Override
  public void setTitle(String inText)
  {
    mLabel.setTitle(inText);
  }

  public HandlerRegistration addClickHandler(ClickHandler inHandler)
  {
    return mLabel.addClickHandler(inHandler);
  }

  public boolean isSource(ClickEvent inEvent)
  {
    return mLabel.equals(inEvent.getSource());
  }

  public void setShow(boolean inShow)
  {
    setVisible(inShow);
  }
}