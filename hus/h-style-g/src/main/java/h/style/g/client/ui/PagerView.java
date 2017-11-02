package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.DropDown;
import org.gwtbootstrap3.client.ui.DropDownHeader;
import org.gwtbootstrap3.client.ui.DropDownMenu;
import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.AnchorInlineDisplay;
import h.style.g.client.model.PagerDisplay;

public class PagerView extends AbstractView implements PagerDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PagerView>
  {
  }


  @UiField
  AnchorInlineView mPrev, mNext;

  @UiField
  DropDown mDropdown, mMaxdrop;

  @UiField
  DropDownMenu mPages, mMaxValues;

  @UiField
  Anchor mPageNum, mMaxNum;

  @UiField
  FlowPanel mPanel;

  @UiField
  InlineLabel mShowing;

  public PagerView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mDropdown.getElement().getStyle().setDisplay(Display.INLINE);
    mMaxdrop.getElement().getStyle().setDisplay(Display.INLINE);
    mPanel.getElement().getStyle().setMarginBottom(20, Unit.PX);
  }

  public void setDropDownStyle(String inStyle)
  {
    mDropdown.addStyleName(inStyle);
  }

  @Override
  public void addMax(int inMax, ClickHandler inHandler)
  {
    AnchorListItem child = new AnchorListItem(String.valueOf(inMax));
    child.addClickHandler(inHandler);
    mMaxValues.add(child);
  }

  @Override
  public void setMaxNum(String inText)
  {
    mMaxNum.setText(inText);
  }

  @Override
  public void setPageNum(String inText)
  {
    mPageNum.setText(inText);
  }

  @Override
  public AnchorInlineDisplay getPrev()
  {
    return mPrev;
  }

  @Override
  public AnchorInlineDisplay getNext()
  {
    return mNext;
  }

  @Override
  public void pagesClear()
  {
    mPages.clear();
  }

  @Override
  public void addGoto()
  {
    mPages.add(new DropDownHeader("Go to page..."));
  }

  @Override
  public void addPage(String inPage, ClickHandler inHandler)
  {
    AnchorListItem child = new AnchorListItem(inPage);
    child.addClickHandler(inHandler);
    mPages.add(child);
  }

  @Override
  public void setShowing(String inText)
  {
    mShowing.setText(inText);
  }
}