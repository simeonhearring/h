package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PageForgotPresenter;

public class PageForgotView extends AbstractView<PageForgotPresenter> implements PageForgotPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageForgotView>
  {
  }

  public PageForgotView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PageForgotPresenter(this);
  }

  @UiField
  Input mEmail;

  @UiField
  Button mForgot;

  @Override
  public void attach(HasWidgets inPanel)
  {
    inPanel.clear();
    inPanel.add(this);
    ((UIObject) inPanel).addStyleName("gray-bg");
  }

  @UiHandler(
  {
      "mForgot"
  })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mForgot.equals(source))
    {
      mPresenter.forgot(mEmail.getText());
    }
  }
}