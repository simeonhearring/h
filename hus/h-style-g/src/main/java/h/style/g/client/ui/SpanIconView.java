package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Cursor;
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

public class SpanIconView extends AbstractView implements IconDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, SpanIconView>
  {
  }

  @UiField
  Span mSpan;

  @UiField
  Icon mIcon;

  boolean mDescending;

  public SpanIconView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  public void setMarginLeft(double inMargin)
  {
    mIcon.setMarginLeft(inMargin);
  }

  public void setMarginRight(double inMargin)
  {
    mIcon.setMarginRight(inMargin);
  }

  @Override
  public boolean isSource(Event<?> inEvent)
  {
    return mIcon.equals(inEvent.getSource());
  }

  @Override
  public void setText(String inText)
  {
    mSpan.setText(inText);
  }

  @Override
  public String getText()
  {
    return mSpan.getText();
  }

  @Override
  public void setEnabled(boolean inEnable)
  {
  }

  @Override
  public void setVisibility(boolean inShow)
  {
    this.getElement().getStyle().setVisibility(inShow ? Visibility.VISIBLE : Visibility.HIDDEN);
  }

  @Override
  public void setShow(boolean inShow)
  {
    setVisible(inShow);
  }

  @Override
  public boolean isShowing()
  {
    return isVisible();
  }

  @Override
  public boolean isEnabled()
  {
    return false;
  }

  @Override
  public HandlerRegistration addClickHandler(ClickHandler inHandler)
  {
    setCursor(Cursor.POINTER);
    return addClickHandler(mIcon, inHandler);
  }

  public void setCursor(Cursor inCursor)
  {
    mIcon.getElement().getStyle().setCursor(inCursor);
  }

  @Override
  public HandlerRegistration setClickHandler(ClickHandler inHandler)
  {
    setCursor(Cursor.POINTER);
    return setClickHandler(mIcon, inHandler);
  }

  @Override
  public HandlerRegistration addDoubleClickHandler(DoubleClickHandler inHandler)
  {
    setCursor(Cursor.POINTER);
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
    mIcon.setType(inType);
  }

  @Override
  public IconType getIcon()
  {
    return mIcon.getType();
  }

  @Override
  public void setIconSpin(boolean inSpin)
  {
    mIcon.setSpin(inSpin);
  }

  @Override
  public void setIconTip(IconType inType, String inTip)
  {
    setIcon(inType);
    setTip(inTip);
  }
}