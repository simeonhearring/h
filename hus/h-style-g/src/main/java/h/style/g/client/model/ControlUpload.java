package h.style.g.client.model;

import org.gwtbootstrap3.client.ui.base.form.AbstractForm.SubmitCompleteHandler;
import org.gwtbootstrap3.client.ui.base.form.AbstractForm.SubmitHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasHTML;

public interface ControlUpload
{
  void setAction(String inAction);

  HandlerRegistration addSubmitCompleteHandler(SubmitCompleteHandler inHandler);

  HandlerRegistration addSubmitHandler(SubmitHandler inHandler);

  void setMethod(String inMethod);

  void setEncoding(String inMethod);

  void setFocus(boolean inFocused);

  void setName(String inName);

  void setEnabled(boolean inEnabled);

  boolean isEnabled();

  String getName();

  void addHidden(String inName, Object inValue);

  void clearHidden();

  void submit();

  ButtonDisplay getUpload();

  void clear();

  String getFilename();

  boolean isSource(ClickEvent inEvent);

  void addClickHandler(ClickHandler inHandler);

  HasHTML getMessage();

  void updateHidden(String inName, Object inValue);

  void init(String inHtml, String inName, String inAction);

  void setVisible(boolean inVisible);
}