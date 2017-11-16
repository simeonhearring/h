package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Placement;

import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.Event;

import h.style.g.client.model.AnchorListItemDisplay;

public class AnchorListItemWrapper extends AbstractView implements AnchorListItemDisplay
{
  private AnchorListItem mIcon;
  private String mProperty;

  public AnchorListItemWrapper(AnchorListItem inIcon)
  {
    mIcon = inIcon;
  }

  @Override
  public void setActive(boolean inActive)
  {
    mIcon.setActive(inActive);
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
  public void setVisibility(boolean inShow)
  {
    mIcon.getElement().getStyle().setVisibility(inShow ? Visibility.VISIBLE : Visibility.HIDDEN);
  }

  @Override
  public void setVisible(boolean inVisible)
  {
    mIcon.setVisible(inVisible);
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
    return register(mIcon.addDomHandler(inHandler, DoubleClickEvent.getType()));
  }

  @Override
  public HandlerRegistration setDoubleClickHandler(DoubleClickHandler inHandler)
  {
    clearHandlers();
    return addDoubleClickHandler(inHandler);
  }

  @Override
  public boolean isSource(Event<?> inEvent)
  {
    if (inEvent.getSource() instanceof Anchor)
    {
      Anchor source = (Anchor) inEvent.getSource();
      return mIcon.equals(source.getParent());
    }
    return false;
  }

  @Override
  public boolean isShowing()
  {
    return mIcon.isVisible();
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

  @Override
  public String getProperty()
  {
    return mProperty;
  }

  @Override
  public void setProperty(String inProperty)
  {
    mProperty = inProperty;
  }

  @Override
  public boolean isSource(ClickEvent inEvent)
  {
    return isSource((Event<?>) inEvent);
  }

  @Override
  public boolean isProperty(String inProperty)
  {
    return mProperty != null && mProperty.equals(inProperty);
  }

  @Override
  public String getProperty(ClickEvent inEvent)
  {
    return null;
  }

  @Override
  public IsWidget getAnchor()
  {
    return mIcon;
  }

  @Override
  public void setPlacement(Placement inPlacement)
  {
  }

  @Override
  public void setTarget(String inTarget)
  {
    Widget widget = ((ComplexPanel) mIcon).getWidget(0);
    ((Anchor) widget).setTarget(inTarget);
  }
}