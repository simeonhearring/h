package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.TextArea;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.TextAreaDisplay;
import h.style.g.client.model.TextAreaDisplay.Resize;

public class TextAreaView extends AbstractTextBox implements TextAreaDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, TextAreaView>
  {
  }

  interface MyStyle extends CssResource
  {
    String resize_vertical();

    String resize_none();

    String resize_horizontal();

    String resize_both();
  }

  @UiField
  TextArea mTextBox;

  @UiField
  MyStyle style;

  public TextAreaView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setElementId(mTextBox.getElement());
    setTextBox(mTextBox);
  }

  public TextArea getTextBox()
  {
    return mTextBox;
  }

  public int getScrollHeight()
  {
    return mTextBox.getElement().getScrollHeight();
  }

  @Override
  public void setResize(Resize inResize)
  {
    mTextBox.removeStyleName(style.resize_none());
    mTextBox.removeStyleName(style.resize_both());
    mTextBox.removeStyleName(style.resize_vertical());
    mTextBox.removeStyleName(style.resize_horizontal());
    switch (inResize)
    {
      case NONE:
        mTextBox.addStyleName(style.resize_none());
        break;
      case BOTH:
        mTextBox.addStyleName(style.resize_both());
        break;
      case VERTICAL:
        mTextBox.addStyleName(style.resize_vertical());
        break;
      case HORIZONTAL:
        mTextBox.addStyleName(style.resize_horizontal());
        break;
      default:
        break;
    }
  }

  @Override
  public void setVisibleLines(int inLines)
  {
    mTextBox.setVisibleLines(inLines);
  }

  @Override
  public void elastic()
  {
    Scheduler.get().scheduleDeferred(new ScheduledCommand()
    {
      @Override
      public void execute()
      {
        elastic(mElementId);
      }
    });
  }

  @Override
  public void elastic(int inSecondsDelay)
  {
    Scheduler.get().scheduleFixedDelay(new RepeatingCommand()
    {
      @Override
      public boolean execute()
      {
        elastic(mElementId);
        return false;
      }
    }, inSecondsDelay * 1000);
  }

  private static void elastic(String inId)
  {
    if (GWT.isClient())
    {
      elasticDelayNative(inId);
    }
  }

  private static native void elasticNative(String inId)
  /*-{
		$wnd.$("#" + inId).elastic();
  }-*/;

  private static native void elasticDelayNative(String inId)
  /*-{
		$wnd.$(document).ready(function()
		{
			$wnd.$("#" + inId).elastic();
		});
  }-*/;
}