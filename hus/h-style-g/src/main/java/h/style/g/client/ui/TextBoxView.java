package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.TextBoxDisplay;

public class TextBoxView extends AbstractTextBox implements TextBoxDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, TextBoxView>
  {
  }

  @UiField
  TextBox mTextBox;

  public TextBoxView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setTextBox(mTextBox);
  }

  public TextBox getTextBox()
  {
    return mTextBox;
  }

   public void autocomplete()
   {
     setAutoComplete(true);
     setElementId(mTextBox.getElement());
     mTextBox.getElement().setAttribute("contenteditable", "true");

     Scheduler.get().scheduleDeferred(new ScheduledCommand()
     {
       @Override
       public void execute()
       {
         autoComplete(mElementId, "Hello", "World", "Hi", "Bye");
       }
     });
   }

  public static native void autoComplete(String inId, String... inAvailableTags)
  /*-{
		var ta = $wnd.$("#" + inId);
		$wnd.$ta.autocomplete({
			source : inAvailableTags
		});
  }-*/;
}