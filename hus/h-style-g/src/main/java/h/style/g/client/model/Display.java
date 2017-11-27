package h.style.g.client.model;

import java.util.Date;

import org.gwtbootstrap3.extras.bootbox.client.callback.ConfirmCallback;
import org.gwtbootstrap3.extras.bootbox.client.callback.PromptCallback;
import org.gwtbootstrap3.extras.bootbox.client.callback.SimpleCallback;

import com.google.gwt.event.dom.client.HumanInputEvent;
import com.google.gwt.event.dom.client.KeyCodeEvent;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.IsWidget;

public interface Display extends IsWidget, HasVisibility
{
  String encrypt(String inText);

  void notify(String inMessage);

  boolean confirm(String inMessage);

  void alert(String inMessage);

  void alert(String inMessage, SimpleCallback inCallback);

  void confirm(String inMessage, ConfirmCallback inCallback);

  void console(String inAction, String inMessage);

  void prompt(String inMessage, PromptCallback inCallback);

  String format(String inPattern, Date inDate);

  Date parse(String inPattern, String inDate);

  void scrollTo(int inLeft, int inTop);

  boolean isShiftKeyDown(HumanInputEvent<?> inEvent);

  boolean isAltKeyDown(HumanInputEvent<?> inEvent);

  boolean isKeyEnter(KeyCodeEvent<?> inEvent);

  boolean isKeyDelete(KeyCodeEvent<?> inEvent);

  boolean isKeyTab(KeyCodeEvent<?> inEvent);

  boolean isShiftKeyDown(KeyCodeEvent<?> inEvent);

  boolean isControlKeyDown(KeyCodeEvent<?> inEvent);

  boolean isAltKeyDown(KeyCodeEvent<?> inEvent);

  int getNativeKeyCode(KeyCodeEvent<?> inEvent);

  void addSessionValue(String inKey, String inValue);
}