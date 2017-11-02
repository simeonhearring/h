package h.style.g.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.NavTabDisplay;
import h.style.g.client.model.TabDisplay;

public class NavTabView extends Composite implements NavTabDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, NavTabView>
  {
  }

  @UiField
  HTMLPanel mPanel;

  @UiField
  UListElement mNav;

  @UiField
  LIElement mNav1, mNav2, mNav3, mNav4, mNav5;

  @UiField
  Anchor mLabel1, mLabel2, mLabel3, mLabel4, mLabel5;

  @UiField
  FlowPanel mTab1, mTab2, mTab3, mTab4, mTab5;

  @UiField
  FlowPanel mLeft, mRight;

  public NavTabView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  @Override
  public void setNavVisible(boolean inVisible)
  {
    mNav.getStyle().setDisplay((inVisible ? Display.BLOCK : Display.NONE));
  }

  private class Tab implements TabDisplay
  {
    private final FlowPanel mTab;
    private final Anchor mLabel;
    private final LIElement mNav;

    public Tab(FlowPanel inTab, Anchor inLabel, LIElement inNav)
    {
      mTab = inTab;
      mLabel = inLabel;
      mNav = inNav;
    }

    @Override
    public void set(String inLabel, IsWidget inWidget)
    {
      setLabel(inLabel);
      setWidget(inWidget);
    }

    @Override
    public void setWidget(IsWidget inWidget)
    {
      mTab.clear();
      mTab.add(inWidget);
    }

    @Override
    public void addClickHandler(ClickHandler inHandler)
    {
      mLabel.addClickHandler(inHandler);
    }

    @Override
    public void setLabel(String inLabel)
    {
      mLabel.setText(inLabel);
    }

    @Override
    public void setShow(boolean inShow)
    {
      mNav.setAttribute("style", "display: " + (inShow ? "block" : "none"));
    }

    @Override
    public void active()
    {
      NavTabView.this.active(mNav, mTab);
    }
  }

  @Override
  public TabDisplay getTab1()
  {
    return new Tab(mTab1, mLabel1, mNav1);
  }

  @Override
  public TabDisplay getTab2()
  {
    return new Tab(mTab2, mLabel2, mNav2);
  }

  @Override
  public TabDisplay getTab3()
  {
    return new Tab(mTab3, mLabel3, mNav3);
  }

  @Override
  public TabDisplay getTab4()
  {
    return new Tab(mTab4, mLabel4, mNav4);
  }

  @Override
  public TabDisplay getTab5()
  {
    return new Tab(mTab5, mLabel5, mNav5);
  }

  @Override
  public void setStyle(String inStyle)
  {
    mPanel.setStyleName(inStyle);
  }

  @Override
  public HasWidgets getLeft()
  {
    return mLeft;
  }

  @Override
  public HasWidgets getRight()
  {
    return mRight;
  }

  @UiHandler(
  {
      "mLabel1", "mLabel2", "mLabel3", "mLabel4", "mLabel5"
  })
  public void onClick(ClickEvent inEvent)
  {
    if (inEvent.getSource() == mLabel1)
    {
      active(mNav1, mTab1);
    }
    else if (inEvent.getSource() == mLabel2)
    {
      active(mNav2, mTab2);
    }
    else if (inEvent.getSource() == mLabel3)
    {
      active(mNav3, mTab3);
    }
    else if (inEvent.getSource() == mLabel4)
    {
      active(mNav4, mTab4);
    }
    else if (inEvent.getSource() == mLabel5)
    {
      active(mNav5, mTab5);
    }
  }

  private void inactive()
  {
    mNav1.setClassName("");
    mNav2.setClassName("");
    mNav3.setClassName("");
    mNav4.setClassName("");
    mNav5.setClassName("");
    mTab1.setStyleName("tab-pane");
    mTab2.setStyleName("tab-pane");
    mTab3.setStyleName("tab-pane");
    mTab4.setStyleName("tab-pane");
    mTab5.setStyleName("tab-pane");
  }

  private void active(Element inNav, FlowPanel inTab)
  {
    inactive();
    inNav.setClassName("active");
    inTab.setStyleName("tab-pane active");
  }
}