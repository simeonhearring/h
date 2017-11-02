package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.constants.Alignment;
import org.gwtbootstrap3.client.ui.constants.DeviceSize;
import org.gwtbootstrap3.client.ui.constants.Emphasis;
import org.gwtbootstrap3.client.ui.constants.Pull;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.HeadingDisplay;

public class HeadingView extends AbstractView implements HeadingDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, HeadingView>
  {
  }

  @UiField
  Heading mHeading;

  public HeadingView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  public void setHtml(String inHtml)
  {
    mHeading.getElement().setInnerHTML(inHtml);
  }

  public void setSubText(String inSubText)
  {
    mHeading.setSubText(inSubText);
  }

  public void setText(String inText)
  {
    mHeading.setText(inText);
  }

  public void setEmphasis(Emphasis inEmphasis)
  {
    mHeading.setEmphasis(inEmphasis);
  }

  public void setVisibleOn(DeviceSize inDeviceSize)
  {
    mHeading.setVisibleOn(inDeviceSize);
  }

  public void setAlignment(Alignment inAlignment)
  {
    mHeading.setAlignment(inAlignment);
  }

  public void setHiddenOn(DeviceSize inDeviceSize)
  {
    mHeading.setHiddenOn(inDeviceSize);
  }

  public void setMarginTop(double inMargin)
  {
    mHeading.setMarginTop(inMargin);
  }

  public void setMarginLeft(double inMargin)
  {
    mHeading.setMarginLeft(inMargin);
  }

  public void setMarginRight(double inMargin)
  {
    mHeading.setMarginRight(inMargin);
  }

  public void setMarginBottom(double inMargin)
  {
    mHeading.setMarginBottom(inMargin);
  }

  public void setPaddingTop(double inPadding)
  {
    mHeading.setPaddingTop(inPadding);
  }

  public void setPaddingLeft(double inPadding)
  {
    mHeading.setPaddingLeft(inPadding);
  }

  public void setPaddingRight(double inPadding)
  {
    mHeading.setPaddingRight(inPadding);
  }

  public void setPaddingBottom(double inPadding)
  {
    mHeading.setPaddingBottom(inPadding);
  }

  public void setColor(String inColor)
  {
    mHeading.setColor(inColor);
  }

  public void setPull(Pull inPull)
  {
    mHeading.setPull(inPull);
  }

  @Override
  public void setHeight(String inHeight)
  {
    mHeading.setHeight(inHeight);
  }

  @Override
  public void setPixelSize(int inWidth, int inHeight)
  {
    mHeading.setPixelSize(inWidth, inHeight);
  }

  @Override
  public void setSize(String inWidth, String inHeight)
  {
    mHeading.setSize(inWidth, inHeight);
  }

  @Override
  public void setStyleDependentName(String inStyleSuffix, boolean inAdd)
  {
    mHeading.setStyleDependentName(inStyleSuffix, inAdd);
  }

  @Override
  public void setStyleName(String inStyle, boolean inAdd)
  {
    mHeading.setStyleName(inStyle, inAdd);
  }

  @Override
  public void setStyleName(String inStyle)
  {
    mHeading.setStyleName(inStyle);
  }

  @Override
  public void setStylePrimaryName(String inStyle)
  {
    mHeading.setStylePrimaryName(inStyle);
  }

  @Override
  public void setTitle(String inTitle)
  {
    mHeading.setTitle(inTitle);
  }

  @Override
  public void setWidth(String inWidth)
  {
    mHeading.setWidth(inWidth);
  }
}