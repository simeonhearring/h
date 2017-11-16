package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.ButtonDismiss;
import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconFlip;
import org.gwtbootstrap3.client.ui.constants.IconPosition;
import org.gwtbootstrap3.client.ui.constants.IconRotate;
import org.gwtbootstrap3.client.ui.constants.IconSize;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Toggle;

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

import h.style.g.client.model.ButtonDisplay;

public class ButtonView extends AbstractView implements ButtonDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ButtonView>
  {
  }

  @UiField
  Button mButton;

  public ButtonView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setElementId(mButton.getElement());
  }

  public ButtonView(Button inButton)
  {
    mButton = inButton;
  }

  public Button getButton()
  {
    return mButton;
  }

  public ButtonView(String inText)
  {
    this();
    setText(inText);
  }

  public ButtonView(String inText, ButtonType inType)
  {
    this();
    setText(inText);
    setType(inType);
  }

  public void makeAnimate()
  {
    makeAnimate(mButton);
  }

  @Override
  public void initElementId()
  {
    setElementId(mButton.getElement());
  }

  @Override
  public void setType(ButtonType inType)
  {
    mButton.setType(inType);
  }

  @Override
  public void setToggle(boolean inToggle)
  {
    Toggle toggle = inToggle ? Toggle.BUTTON : null;
    mButton.setDataToggle(toggle);
  }

  @Override
  public boolean isToggled()
  {
    return Toggle.BUTTON.equals(mButton.getDataToggle());
  }

  @Override
  public void setBlock(boolean inBlock)
  {
    mButton.setBlock(inBlock);
  }

  @Override
  public ButtonType getType()
  {
    return mButton.getType();
  }

  @Override
  public void setSize(ButtonSize inType)
  {
    mButton.setSize(inType);
  }

  @Override
  public ButtonSize getSize()
  {
    return mButton.getSize();
  }

  @Override
  public void setLoadingText(String inLoadingText)
  {
    mButton.setDataLoadingText(inLoadingText);
  }

  @Override
  public void setIcon(IconType inType)
  {
    mButton.setIcon(inType);
  }

  @Override
  public IconType getIcon()
  {
    return mButton.getIcon();
  }

  @Override
  public void setIconTip(IconType inType, String inTip)
  {
    setIcon(inType);
    setTip(inTip);
  }

  @Override
  public boolean isSource(Event<?> inEvent)
  {
    return mButton.equals(inEvent.getSource());
  }

  @Override
  public void setText(String inText)
  {
    mButton.setText(inText);
  }

  @Override
  public String getText()
  {
    return mButton.getText();
  }

  @Override
  public void setEnabled(boolean inEnable)
  {
    mButton.setEnabled(inEnable);
  }

  @Override
  public void setVisible(boolean inVisible)
  {
    mButton.setVisible(inVisible);
  }

  @Override
  public void setVisibility(boolean inShow)
  {
    mButton.getElement().getStyle().setVisibility(inShow ? Visibility.VISIBLE : Visibility.HIDDEN);
  }

  @Override
  public void setTitle(String inTitle)
  {
    mButton.setTitle(inTitle);
  }

  @Override
  public void setShow(boolean inShow)
  {
    mButton.setVisible(inShow);
  }

  @Override
  public void setWidth(String inWidth)
  {
    mButton.setWidth(inWidth);
  }

  @Override
  public boolean isShowing()
  {
    return mButton.isVisible();
  }

  @Override
  public boolean isEnabled()
  {
    return mButton.isEnabled();
  }

  @Override
  public HandlerRegistration addClickHandler(ClickHandler inHandler)
  {
    return addClickHandler(mButton, inHandler);
  }

  @Override
  public HandlerRegistration setClickHandler(ClickHandler inHandler)
  {
    return setClickHandler(mButton, inHandler);
  }

  @Override
  public HandlerRegistration addDoubleClickHandler(DoubleClickHandler inHandler)
  {
    return register(mButton.addDomHandler(inHandler, DoubleClickEvent.getType()));
  }

  @Override
  public HandlerRegistration setDoubleClickHandler(DoubleClickHandler inHandler)
  {
    clearHandlers();
    return addDoubleClickHandler(inHandler);
  }

  @Override
  public void setIconSpin(boolean inSpin)
  {
    mButton.setIconSpin(inSpin);
  }

  @Override
  public void setDataDismiss(ButtonDismiss inDismiss)
  {
    mButton.setDataDismiss(inDismiss);
  }

  public void setIconPosition(IconPosition inIconPosition)
  {
    mButton.setIconPosition(inIconPosition);
  }

  public void setIconSize(IconSize inIconSize)
  {
    mButton.setIconSize(inIconSize);
  }

  public void setIconFlip(IconFlip inIconFlip)
  {
    mButton.setIconFlip(inIconFlip);
  }

  public void setIconRotate(IconRotate inIconRotate)
  {
    mButton.setIconRotate(inIconRotate);
  }

  public void setIconBordered(boolean inIconBordered)
  {
    mButton.setIconBordered(inIconBordered);
  }

  public void setIconInverse(boolean inIconInverse)
  {
    mButton.setIconInverse(inIconInverse);
  }

  public void setIconPulse(boolean inIconPulse)
  {
    mButton.setIconPulse(inIconPulse);
  }

  public void setIconFixedWidth(boolean inIconFixedWidth)
  {
    mButton.setIconFixedWidth(inIconFixedWidth);
  }

  public void setPaddingRight(double inPadding)
  {
    mButton.setPaddingRight(inPadding);
  }

  public void setMarginRight(double inMargin)
  {
    mButton.setMarginRight(inMargin);
  }

  // @Override
  // public HandlerRegistration addBlurHandler(BlurHandler inHandler)
  // {
  // return addBlurHandler(mButton, inHandler);
  // }
  //
  // @Override
  // public HandlerRegistration addFocusHandler(FocusHandler inHandler)
  // {
  // return addFocusHandler(mButton, inHandler);
  // }
  //
  // @Override
  // public HandlerRegistration addMouseOverHandler(MouseOverHandler inHandler)
  // {
  // return addMouseOverHandler(mButton, inHandler);
  // }
  //
  // @Override
  // public HandlerRegistration addMouseOutHandler(MouseOutHandler inHandler)
  // {
  // return addMouseOutHandler(mButton, inHandler);
  // }
}