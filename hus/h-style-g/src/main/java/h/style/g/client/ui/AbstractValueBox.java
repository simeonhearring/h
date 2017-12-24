package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.base.ValueBoxBase;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.Event;

abstract class AbstractValueBox<T> extends AbstractView
{
  protected ValueBoxBase<T> mValueBox;

  public void setTextBox(ValueBoxBase<T> inTextBox)
  {
    mValueBox = inTextBox;
  }

  @Override
  protected void onUnload()
  {
    super.onUnload();
    clearHandlers();
  }

  public void setPlaceholder(String inPlaceHolder)
  {
    mValueBox.setPlaceholder(inPlaceHolder);
  }

  public void setMaxLength(int inLength)
  {
    mValueBox.setMaxLength(inLength);
  }

  @Override
  public void setWidth(String inWidth)
  {
    mValueBox.setWidth(inWidth);
  }

  @Override
  public void setHeight(String inHeight)
  {
    mValueBox.setHeight(inHeight);
  }

  public boolean isSource(Event<?> inEvent)
  {
    return mValueBox.equals(inEvent.getSource());
  }

  public T getValue()
  {
    return mValueBox.getValue();
  }

  public void setValue(T inValue)
  {
    mValueBox.setValue(inValue);
  }

  public void setText(String inText)
  {
    mValueBox.setText(inText);
  }

  public void setEnabled(boolean inEnable)
  {
    mValueBox.setEnabled(inEnable);
  }

  public boolean isEnabled()
  {
    return mValueBox.isEnabled();
  }

  public void setFocus(boolean inFocus)
  {
    mValueBox.setFocus(inFocus);
  }

  public void setAutoComplete(boolean inAutoComplete)
  {
    mValueBox.setAutoComplete(inAutoComplete);
  }

  public void setAllowBlank(boolean inAllowBlank)
  {
    mValueBox.setAllowBlank(inAllowBlank);
  }

  public void setReadOnly(boolean inReadOnly)
  {
    mValueBox.setReadOnly(inReadOnly);
  }

  public void focus(final boolean inAtEnd)
  {
    Scheduler.get().scheduleDeferred(new ScheduledCommand()
    {
      @Override
      public void execute()
      {
        setFocus(true);
        if (inAtEnd)
        {
          // TODO mValueBox.setCursorPos(mValueBox.getValue().length());
        }
      }
    });
  }

  public void focus(Integer inDelay, final boolean inAtEnd)
  {
    if (inDelay != null)
    {
      Scheduler.get().scheduleFixedDelay(new RepeatingCommand()
      {
        @Override
        public boolean execute()
        {
          setFocus(true);
          if (inAtEnd)
          {
            // TODO mValueBox.setCursorPos(mValueBox.getValue().length());
          }
          return false;
        }
      }, inDelay);
    }
    else
    {
      Scheduler.get().scheduleDeferred(new ScheduledCommand()
      {
        @Override
        public void execute()
        {
          setFocus(true);
        }
      });
    }
  }

  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> inHandler)
  {
    return register(mValueBox.addValueChangeHandler(inHandler));
  }

  public HandlerRegistration addClickHandler(ClickHandler inHandler)
  {
    return register(mValueBox.addClickHandler(inHandler));
  }

  public HandlerRegistration addChangeHandler(ChangeHandler inHandler)
  {
    return register(mValueBox.addChangeHandler(inHandler));
  }

  public HandlerRegistration addKeyUpHandler(KeyUpHandler inHandler)
  {
    return register(mValueBox.addKeyUpHandler(inHandler));
  }

  public HandlerRegistration addKeyDownHandler(KeyDownHandler inHandler)
  {
    return register(mValueBox.addKeyDownHandler(inHandler));
  }

  public HandlerRegistration addKeyPressHandler(KeyPressHandler inHandler)
  {
    return register(mValueBox.addKeyPressHandler(inHandler));
  }

  public HandlerRegistration addFocusHandler(FocusHandler inHandler)
  {
    return register(mValueBox.addFocusHandler(inHandler));
  }

  public HandlerRegistration addBlurHandler(BlurHandler inHandler)
  {
    return register(mValueBox.addBlurHandler(inHandler));
  }
}