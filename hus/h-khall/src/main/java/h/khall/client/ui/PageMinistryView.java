package h.khall.client.ui;

import java.util.Date;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.html.OrderedList;
import org.gwtbootstrap3.extras.tagsinput.client.callback.ItemTextCallback;
import org.gwtbootstrap3.extras.tagsinput.client.callback.ItemValueCallback;
import org.gwtbootstrap3.extras.tagsinput.client.callback.OnTagExistsCallback;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemAddedEvent;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemAddedHandler;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemRemovedEvent;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemRemovedHandler;
import org.gwtbootstrap3.extras.tagsinput.client.ui.base.MultiValueTagsInput;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.pages.PageMinistryPresenter;
import h.model.shared.Tag;
import h.model.shared.util.TimeUtil;

public class PageMinistryView extends AbstractView<PageMinistryPresenter>
  implements ItemTextCallback<Tag>, ItemValueCallback<Tag>, ItemAddedHandler<Tag>,
  ItemRemovedHandler<Tag>, OnTagExistsCallback<Tag>, PageMinistryPresenter.Display, ClickHandler
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageMinistryView>
  {
  }

  @UiField
  StatsView mStats, mPubStats;

  @UiField
  MultiValueTagsInput<Tag> mName;

  @UiField
  Input mMonth;

  @UiField
  MinistryMonthView mMonthM;

  @UiField
  MinistryYearView mYearM;

  @UiField
  ListBox mYm, mType;

  @UiField
  OrderedList mNames, mPublishers;

  @UiField
  FsgView mFsg;

  private PersonSet mDataset;
  private TakesDate mMonthV;

  public PageMinistryView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PageMinistryPresenter(this).handlers();

    mStats.mDiv.add(new ThresholdView());

    mMonthV = new TakesDate(mMonth);
    mMonthV.setValue(TimeUtil.getFirstOfMonth(TimeUtil.currentYear(), TimeUtil.currentServiceMonth()));

    mDataset = new PersonSet();

    mName.setItemText(this);
    mName.setItemValue(this);
    mName.onTagExists(this);

    mName.addItemAddedHandler(this);
    mName.addItemRemovedHandler(this);

    mName.setDatasets(mDataset);

    mYm.addChangeHandler(mPresenter);
    mType.addChangeHandler(mPresenter);

    mFsg.setExtra(true);
  }

  @UiHandler(
  {
      "mMonth", "mFsg"
  })
  public void onChange(ChangeEvent inEvent)
  {
    if (mFsg.isSource(inEvent))
    {
      mPresenter.filterFsg(mFsg.getSelectedValue());
      mYearM.chartFsg(mFsg.getSelectedValue());
    }
    else
    {
      mMonthM.changeMonth(ym());
      mYearM.changeMonth(ym());
    }
  }

  @Override
  public void clearYm()
  {
    mYm.clear();
  }

  @Override
  public void addYm(String inItem, String inValue)
  {
    mYm.addItem(inItem, inValue);
  }

  @Override
  public String getYm()
  {
    return mYm.getSelectedValue();
  }

  @Override
  public void clearType()
  {
    mType.clear();
  }

  @Override
  public void addType(String inItem, String inValue)
  {
    mType.addItem(inItem, inValue);
  }

  @Override
  public String getType()
  {
    return mType.getSelectedValue();
  }

  @Override
  public void clearName()
  {
    mNames.clear();
  }

  @Override
  public void addName(String inItem, String inValue)
  {
    AnchorListItem w = new AnchorListItem(inItem);
    w.setId(inValue);
    w.addClickHandler(this);
    mNames.add(w);
  }

  @Override
  public void clearPublisher()
  {
    mPublishers.clear();
  }

  @Override
  public void addPublishers(String inItem, String inValue)
  {
    AnchorListItem w = new AnchorListItem(inItem);
    w.setId(inValue);
    w.addClickHandler(this);
    mPublishers.add(w);
  }

  @Override
  public void onClick(ClickEvent inEvent)
  {
    AnchorListItem source = (AnchorListItem) ((Widget) inEvent.getSource()).getParent();
    String id = source.getId();
    changePub(id);
  }

  private void changePub(String inPubId)
  {
    Long pubId = Long.valueOf(inPubId);
    int[] ym = ym();
    mMonthM.changePub(pubId, ym);
    mYearM.changePub(pubId, ym);
  }

  @Override
  public void onTagExists(Tag inPerson, Element inTag)
  {
    notify(inPerson.getName() + " exists, no duplicates.");
  }

  @Override
  public void onItemAdded(ItemAddedEvent<Tag> inEvent)
  {
    String id = inEvent.getItem().gId();
    changePub(id);
  }

  @Override
  public void onItemRemoved(ItemRemovedEvent<Tag> inEvent)
  {
    Long pubId = null;
    int[] ym = ym();
    mMonthM.changePub(pubId, ym);
    mYearM.changePub(pubId, ym);
  }

  @Override
  public String getItemValue(Tag inItem)
  {
    return inItem.getValue();
  }

  @Override
  public String getItemText(Tag inItem)
  {
    return inItem.getName();
  }

  @SuppressWarnings("deprecation")
  private int[] ym()
  {
    int year = TimeUtil.currentYear();
    int month = TimeUtil.currentServiceMonth();

    Date date = mMonthV.getValue();
    if (date != null)
    {
      year = date.getYear() + 1900;
      month = date.getMonth() + 1;

      mMonthV.setValue(TimeUtil.getFirstOfMonth(year, month));
    }

    return new int[]
    {
        year, month
    };
  }

  @Override
  protected void onLoad()
  {
    slimScroll();
  }
}