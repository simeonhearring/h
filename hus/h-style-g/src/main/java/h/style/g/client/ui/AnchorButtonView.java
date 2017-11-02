package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.AnchorButton;
import org.gwtbootstrap3.client.ui.constants.ButtonDismiss;
import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
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

public class AnchorButtonView extends AbstractView implements ButtonDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, AnchorButtonView>
  {
  }

  @UiField
  AnchorButton mButton;

  public AnchorButtonView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setElementId(mButton.getElement());
  }

  public AnchorButtonView(AnchorButton inButton)
  {
    mButton = inButton;
  }

  public AnchorButtonView(String inText)
  {
    this();
    setText(inText);
  }

  public AnchorButtonView(String inText, ButtonType inType)
  {
    this();
    setText(inText);
    setType(inType);
  }

  @Override
  public void initElementId()
  {
    setElementId(mButton.getElement());
  }

  public void setFontSize(double inValue)
  {
    mButton.getElement().getStyle().setFontSize(inValue, Unit.PX);
  }

  public void setMarginBottom(double inMargin)
  {
    mButton.setMarginBottom(inMargin);
  }

  public void setMarginLeft(double inMargin)
  {
    mButton.setMarginLeft(inMargin);
  }

  public void setMarginRight(double inMargin)
  {
    mButton.setMarginRight(inMargin);
  }

  public void setMarginTop(double inMargin)
  {
    mButton.setMarginTop(inMargin);
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
    // mButton.setText(inText);
    mButton.getElement().setInnerHTML(inText);
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
    return add(mButton.addDomHandler(inHandler, DoubleClickEvent.getType()));
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