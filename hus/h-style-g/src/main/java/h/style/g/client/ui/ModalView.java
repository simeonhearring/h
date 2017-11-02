package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.gwtbootstrap3.client.ui.ModalFooter;
import org.gwtbootstrap3.client.ui.ModalSize;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.ButtonDisplay;
import h.style.g.client.model.ModalDisplay;

public class ModalView extends AbstractView implements ModalDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ModalView>
  {
  }

  @UiField
  Modal mPop;

  @UiField
  ModalBody mBody;

  @UiField
  ModalFooter mFooter;

  @UiField
  CheckBox mCheck;

  @UiField
  ButtonView mSave, mClose;

  public ModalView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mClose.addStyleName("btn-default");
  }

  public ModalView(String inCaption)
  {
    this();
    mPop.setTitle(inCaption);
  }

  @Override
  public void addBody(IsWidget inWidget)
  {
    mBody.add(inWidget);
  }

  @Override
  public void addFooter(IsWidget inWidget)
  {
    mFooter.add(inWidget);
  }

  @Override
  public void setCloseVisible(boolean inShow)
  {
    mPop.setClosable(inShow);
  }

  // @Override
  // public void addShowHandler(ShowHandler inHandler)
  // {
  // mPop.addShowHandler(inHandler);
  // }
  //
  // @Override
  // public void addHideHandler(HideHandler inHandler)
  // {
  // mPop.addHideHandler(inHandler);
  // }

  @Override
  public void setHeight(String inHeight)
  {
    mPop.setHeight(inHeight);
  }

  @Override
  public void setWidth(String inWidth)
  {
    mPop.setWidth(inWidth);
  }

  @Override
  public HasText getHeader()
  {
    return new HasText()
    {
      @Override
      public void setText(String inText)
      {
        mPop.setTitle(inText);
      }

      @Override
      public String getText()
      {
        return mPop.getTitle();
      }
    };
  }

  @Override
  public void setShow(boolean inShow)
  {
    if (inShow)
    {
      mPop.show();
    }
    else
    {
      mPop.hide();
    }
  }

  public Modal getPop()
  {
    return mPop;
  }

  @Override
  public void setSize(ModalSize inSize)
  {
    mPop.setSize(inSize);
  }

  @Override
  public boolean isShowing()
  {
    return mPop.isVisible();
  }

  @Override
  public ButtonDisplay getSave()
  {
    return mSave;
  }

  @Override
  public ButtonDisplay getClose()
  {
    return mClose;
  }

  @Override
  public boolean isChecked()
  {
    return mCheck.getValue();
  }

  @Override
  public void setCheckText(String inText)
  {
    mCheck.setHTML(inText);
  }

  @Override
  public void setCheckVisible(boolean inVisible)
  {
    mCheck.setVisible(inVisible);
  }
}