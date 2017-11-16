package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.IconType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.Event;

import h.style.g.client.model.IconDisplay;
import h.style.g.client.model.SortIconDisplay;

public class IconView extends AbstractView implements IconDisplay, SortIconDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, IconView>
  {
  }

  @UiField
  Button mIcon;

  boolean mDescending;

  public IconView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setElementId(mIcon.getElement());
    setAnimateObject(mIcon);
  }

  @Override
  public void setDescending(boolean inDescending)
  {
    mDescending = inDescending;
  }

  public boolean isDescending()
  {
    return mDescending;
  }

  public IconView(Button inIcon)
  {
    mIcon = inIcon;
  }

  public IconView(IconType inIconType)
  {
    this();
    mIcon.setIcon(inIconType);
  }

  public IconView(IconType inIconType, String inTitle)
  {
    this(inIconType);
    mIcon.setTitle(inTitle);
  }

  public IconView(IconType inIconType, String inTitle, String inStyle)
  {
    this(inIconType);
    mIcon.setTitle(inTitle);
    addStyleName(inStyle);
  }

  public IconView makeRemoveIconType()
  {
    mIcon.setIcon(IconType.ADJUST);
    mIcon.getWidget(0).setStyleName("glyphicon glyphicon-remove");
    return this;
  }

  @Override
  public boolean isSource(Event<?> inEvent)
  {
    return mIcon.equals(inEvent.getSource());
  }

  @Override
  public void setText(String inText)
  {
    mIcon.setText(inText);
  }

  @Override
  public String getText()
  {
    return mIcon.getText();
  }

  @Override
  public void setEnabled(boolean inEnable)
  {
    mIcon.setEnabled(inEnable);
  }

  @Override
  public void setVisible(boolean inVisible)
  {
    mIcon.setVisible(inVisible);
  }

  @Override
  public void setVisibility(boolean inShow)
  {
    mIcon.getElement().getStyle().setVisibility(inShow ? Visibility.VISIBLE : Visibility.HIDDEN);
  }

  @Override
  public void setTitle(String inTitle)
  {
    mIcon.setTitle(inTitle);
  }

  @Override
  public void setShow(boolean inShow)
  {
    mIcon.setVisible(inShow);
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
  public void setIconSpin(boolean inSpin)
  {
    mIcon.setIconSpin(inSpin);
  }

  @Override
  public void setIconTip(IconType inType, String inTip)
  {
    setIcon(inType);
    setTip(inTip);
  }
}