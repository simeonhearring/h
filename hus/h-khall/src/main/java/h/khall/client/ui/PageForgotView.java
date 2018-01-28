package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
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

import h.khall.client.model.pages.PageForgotPresenter;
import h.style.g.client.ui.util.StorageUtil;

public class PageForgotView extends AbstractView<PageForgotPresenter> implements PageForgotPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageForgotView>
  {
  }

  @UiField
  Input mEmail, mEncrypt;

  @UiField
  Button mForgot;

  @UiField
  Anchor mCancel;

  public PageForgotView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PageForgotPresenter(this);

    if (StorageUtil.hasEncrypt())
    {
      mEmail.setText(StorageUtil.getEncryptKey("email"));
      mEncrypt.setValue(StorageUtil.getEncryptKey(null));
    }
  }

  @Override
  public void attach(HasWidgets inPanel)
  {
    inPanel.clear();
    inPanel.add(this);
    ((UIObject) inPanel).addStyleName("gray-bg");
  }

  @UiHandler(
  {
      "mForgot", "mCancel"
  })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mForgot.equals(source))
    {
      mPresenter.forgot(mEmail.getText(), mEncrypt.getText());
    }
    else if (mCancel.equals(source))
    {
      mPresenter.cancel();
    }
  }
}