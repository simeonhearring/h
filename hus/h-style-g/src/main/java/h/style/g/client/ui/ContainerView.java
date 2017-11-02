package h.style.g.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.ContainerDisplay;
import h.style.g.client.model.NavTabDisplay;

public class ContainerView extends Composite implements ContainerDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ContainerView>
  {
  }

  @UiField
  HTMLPanel mHead, mFoot;

  @UiField
  FlowPanel mBody;

  public ContainerView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  @SuppressWarnings("unchecked")
  @Override
  public HasWidgets getHead()
  {
    return mHead;
  }

  @SuppressWarnings("unchecked")
  @Override
  public HasWidgets getBody()
  {
    return mBody;
  }

  @SuppressWarnings("unchecked")
  @Override
  public HasWidgets getFoot()
  {
    return mFoot;
  }

  @Override
  public NavTabDisplay addNavTab()
  {
    NavTabView ret = new NavTabView();
    mBody.add(ret);
    return ret;
  }
}