package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Pager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.MidweekPresenter;
import h.khall.client.model.MonthPresenter;

public class MidweekView extends AbstractView<MidweekPresenter> implements MidweekPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, MidweekView>
  {
  }

  @UiField
  Pager mPager;

  @UiField
  MonthView mM0, mM1, mM2;

  public MidweekView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new MidweekPresenter(this).handlers();

    mPager.addNextClickHandler(new ClickHandler()
    {
      @Override
      public void onClick(ClickEvent inEvent)
      {
        mPresenter.next();
      }
    });

    mPager.addPreviousClickHandler(new ClickHandler()
    {
      @Override
      public void onClick(ClickEvent inEvent)
      {
        mPresenter.previous();
      }
    });
  }

  @Override
  public MonthPresenter.Display getMonth0()
  {
    return mM0;
  }

  @Override
  public MonthPresenter.Display getMonth1()
  {
    return mM1;
  }

  @Override
  public MonthPresenter.Display getMonth2()
  {
    return mM2;
  }
}