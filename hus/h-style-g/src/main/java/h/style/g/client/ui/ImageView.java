package h.style.g.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.Event;

import h.style.g.client.model.ImageDisplay;

public class ImageView extends AbstractView implements ImageDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ImageView>
  {
  }

  @UiField
  Image mImage;

  HandlerRegistration mHandlerRegistration;

  public ImageView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  public ImageView(ImageResource inResource)
  {
    this();
    setResource(inResource);
  }

  public Image getImage()
  {
    return mImage;
  }

  @Override
  public void setMarginTop(double inValue)
  {
    mImage.getElement().getStyle().setMarginTop(inValue, Unit.PX);
  }

  @Override
  public void setMarginBottom(double inValue)
  {
    mImage.getElement().getStyle().setMarginBottom(inValue, Unit.PX);
  }

  @Override
  public void setMarginLeft(double inValue)
  {
    mImage.getElement().getStyle().setMarginLeft(inValue, Unit.PX);
  }

  @Override
  public void setMarginRight(double inValue)
  {
    mImage.getElement().getStyle().setMarginRight(inValue, Unit.PX);
  }

  public void makeAnimate()
  {
    makeAnimate(mImage);
  }

  @Override
  public void setShow(boolean inShow)
  {
    setVisible(inShow);
    mImage.setVisible(inShow);
  }

  @Override
  public void setResource(ImageResource inResource)
  {
    mImage.setResource(inResource);
  }

  @Override
  protected void onUnload()
  {
    super.onUnload();
    if (mHandlerRegistration != null)
    {
      mHandlerRegistration.removeHandler();
    }
  }

  @Override
  public boolean isSource(Event<?> inEvent)
  {
    return mImage.equals(inEvent.getSource());
  }

  @Override
  public HandlerRegistration addClickHandler(ClickHandler inHandler)
  {
    return mImage.addClickHandler(inHandler);
  }

  @Override
  public boolean isShowing()
  {
    return mImage.isVisible();
  }

  @Override
  public void setUrl(String inUrl)
  {
    mImage.setUrl(inUrl);
  }

  @Override
  public void setHeight(String inHeight)
  {
    mImage.setHeight(inHeight);
  }

  @Override
  public void setWidth(String inWidth)
  {
    mImage.setWidth(inWidth);
  }

  public void setSize(String inSize)
  {
    String[] size = inSize.split(",");
    setWidth(size[0] + "px");
    setHeight(size[1] + "px");
  }
}