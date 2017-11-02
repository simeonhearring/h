package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.base.form.AbstractForm.SubmitCompleteHandler;
import org.gwtbootstrap3.client.ui.base.form.AbstractForm.SubmitHandler;
import org.gwtbootstrap3.client.ui.constants.InputType;
import org.gwtbootstrap3.client.ui.gwt.FlowPanel;
import org.gwtbootstrap3.client.ui.gwt.FormPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.ButtonDisplay;
import h.style.g.client.model.ControlUpload;
import h.style.g.client.ui.util.JsniUtil;

public class FileUploadView extends AbstractView implements ControlUpload
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, FileUploadView>
  {
  }

  @UiField
  Form mForm;

  @UiField
  HTML mMessage;

  @UiField
  Input mFile;

  @UiField
  ButtonView mUpload;

  @UiField
  FlowPanel mHidden;

  public FileUploadView()
  {
    initWidget(BINDER.createAndBindUi(this));

    if (JsniUtil.isIEBrowser())
    {
      mFile.getElement().getStyle().setHeight(40, Unit.PX);
    }
  }

  @Override
  public void clear()
  {
    mFile.setValue(null);
  }

  @Override
  public String getFilename()
  {
    return mFile.getValue();
  }

  @Override
  public ButtonDisplay getUpload()
  {
    return mUpload;
  }

  @Override
  public void submit()
  {
    mForm.submit();
  }

  @Override
  public void addHidden(String inName, Object inValue)
  {
    Input name = new Input(InputType.TEXT);
    name.setName(inName);
    name.setValue(String.valueOf(inValue));
    name.setVisible(false);
    mHidden.add(name);
  }

  @Override
  public void updateHidden(String inName, Object inValue)
  {
    int count = mHidden.getWidgetCount();
    for (int i = 0; i < count; i++)
    {
      Widget w = mHidden.getWidget(i);
      if (w instanceof Input && ((Input) w).getName().equals(inName))
      {
        ((Input) w).setValue(String.valueOf(inValue));
        break;
      }
    }
  }

  @Override
  public void clearHidden()
  {
    mHidden.clear();
  }

  @Override
  public void setAction(String inAction)
  {
    mForm.setAction(inAction);
  }

  @Override
  public HandlerRegistration addSubmitCompleteHandler(SubmitCompleteHandler inHandler)
  {
    return mForm.addSubmitCompleteHandler(inHandler);
  }

  @Override
  public HandlerRegistration addSubmitHandler(SubmitHandler inHandler)
  {
    return mForm.addSubmitHandler(inHandler);
  }

  @Override
  public void setMethod(String inMethod)
  {
    mForm.setMethod(inMethod);
  }

  @Override
  public void setEncoding(String inMethod)
  {
    mForm.setEncoding(inMethod);
  }

  @Override
  public String getName()
  {
    return mFile.getName();
  }

  @Override
  public boolean isEnabled()
  {
    return mFile.isEnabled();
  }

  @Override
  public void setEnabled(boolean inEnabled)
  {
    mFile.setEnabled(inEnabled);
  }

  @Override
  public void setName(String inName)
  {
    mFile.setName(inName);
  }

  @Override
  public void setFocus(boolean inFocused)
  {
    mFile.setFocus(inFocused);
  }

  @Override
  public boolean isSource(ClickEvent inEvent)
  {
    return mUpload.isSource(inEvent);
  }

  @Override
  public void addClickHandler(ClickHandler inHandler)
  {
    mUpload.addClickHandler(inHandler);
  }

  @Override
  public HasHTML getMessage()
  {
    return mMessage;
  }

  @Override
  public void init(String inHtml, String inName, String inAction)
  {
    setEncoding(FormPanel.ENCODING_MULTIPART);
    setMethod(FormPanel.METHOD_POST);
    setName(inName);
    setAction(inAction);
    mMessage.setHTML(inHtml);
    Style s = mMessage.getElement().getStyle();
    s.setWidth(350, Unit.PX);
    s.setFontStyle(FontStyle.ITALIC);
    s.setFontSize(12, Unit.PX);
  }
}