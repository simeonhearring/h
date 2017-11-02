package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Placement;

import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.Event;

import h.style.g.client.model.IconDisplay;

public class IconAnchorWrapper extends AbstractView implements IconDisplay
{
  Anchor mIcon;

  public IconAnchorWrapper(Anchor inIcon)
  {
    mIcon = inIcon;
  }

  public IconAnchorWrapper(Anchor inIcon, IconType inType, String inText, String inStyle)
  {
    mIcon = inIcon;
    setIcon(inType);
    setText(inText);
    addStyleName(inStyle);
  }

  @Override
  public Widget asWidget()
  {
    return mIcon;
  }

  @Override
  public void setEnabled(boolean inEnable)
  {
    mIcon.setEnabled(inEnable);
  }

  @Override
  public void setShow(boolean inShow)
  {
    mIcon.setVisible(inShow);
  }

  @Override
  public void setText(String inText)
  {
    mIcon.setText(inText);
  }

  @Override
  public HandlerRegistration addClickHandler(ClickHandler inHandler)
  {
    return addClickHandler(mIcon, inHandler);
  }

  @Override
  public HandlerRegistration setClickHandler(ClickHandler inHandler)
  {
    return setClickHandler(mIcon, inHandler);
  }

  @Override
  public HandlerRegistration addDoubleClickHandler(DoubleClickHandler inHandler)
  {
    return addDoubleClickHandler(mIcon, inHandler);
  }

  @Override
  public HandlerRegistration setDoubleClickHandler(DoubleClickHandler inHandler)
  {
    return setDoubleClickHandler(mIcon, inHandler);
  }

  @Override
  public boolean isSource(Event<?> inEvent)
  {
    return mIcon.equals(inEvent.getSource());
  }

  @Override
  public boolean isShowing()
  {
    return mIcon.isVisible();
  }

  @Override
  public void setVisibility(boolean inShow)
  {
    mIcon.getElement().getStyle().setVisibility(inShow ? Visibility.VISIBLE : Visibility.HIDDEN);
  }

  @Override
  public boolean isEnabled()
  {
    return mIcon.isEnabled();
  }

  public void setTitlePlacement(Placement inPlacement)
  {
    mTooltip.setPlacement(inPlacement);
    // mTooltip.reconfigure();
  }

  @Override
  public void setIcon(IconType inType)
  {
    mIcon.setIcon(inType);
  }

  @Override
  public IconType getIcon()
  {
    return mIcon.getIcon();
  }

  @Override
  public void addStyleName(String inStyleName)
  {
    mIcon.addStyleName(inStyleName);
  }

  @Override
  public String getText()
  {
    return mIcon.getText();
  }

  @Override
  public void setIconTip(IconType inType, String inTip)
  {
    setIcon(inType);
    setTip(inTip);
  }

  @Override
  public void setIconSpin(boolean inSpin)
  {
    mIcon.setIconSpin(inSpin);
  }
}