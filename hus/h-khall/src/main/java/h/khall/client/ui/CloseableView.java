package h.khall.client.ui;

import java.util.Iterator;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;
import org.gwtbootstrap3.client.ui.html.Span;
import org.gwtbootstrap3.extras.animate.client.ui.constants.Animation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.ui.AbstractView;

public class CloseableView extends AbstractView implements HasWidgets
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, CloseableView>
  {
  }

  @UiField
  HTMLPanel mTop, mContent;

  @UiField
  Anchor mCollaspe, mRemove;

  @UiField
  Heading mHeading;

  @UiField
  Span mSubHeading;

  public CloseableView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setElementId(mTop.getElement());
    setElementId(mContent.getElement());
  }

  @UiHandler(
      {
        "mRemove", "mCollaspe"
      })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mRemove.equals(source))
    {
      removeFromParent();
    }
    else if (mCollaspe.equals(source))
    {
      // slideToggle(mTop.getId(), mContent.getId());

      boolean visible = !mContent.isVisible();
      mContent.setVisible(visible);
      mCollaspe.setIcon(visible ? IconType.CHEVRON_UP : IconType.CHEVRON_DOWN);
      styleName(mTop, !visible, "border-bottom");

      styleName(mContent, false, Animation.FADE_IN.getCssName());
      styleName(mContent, false, Animation.FADE_OUT.getCssName());
      Animation animate = visible ? Animation.FADE_IN : Animation.FADE_OUT;
      animate(mContent, animate, 1, 750, 0);
    }
  }

  private static native void slideToggle(String inTopId, String inContentId)
  /*-{
		var content = $wnd.$(inContentId);
		content.slideToggle(200);
  }-*/;

  @Override
  public void add(Widget inW)
  {
    mContent.add(inW);
  }

  @Override
  public void clear()
  {
    mContent.clear();
  }

  @Override
  public Iterator<Widget> iterator()
  {
    return mContent.iterator();
  }

  @Override
  public boolean remove(Widget inW)
  {
    return mContent.remove(inW);
  }

  public void setHeading(String inText)
  {
    mHeading.setText(inText);
  }

  public void setSubHeading(String inText)
  {
    mSubHeading.setText(inText);
  }
}