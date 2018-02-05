package h.khall.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.html.OrderedList;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.pages.PageMissingPresenter;
import h.model.shared.util.NumberUtil;
import h.style.g.client.ui.TableView;

public class PageMissingView extends AbstractView<PageMissingPresenter>
  implements PageMissingPresenter.Display, ClickHandler
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageMissingView>
  {
  }

  @UiField
  OrderedList mPublishers;

  @UiField
  FsgView mFsg;

  @UiField
  Heading mHeading, mTitle;

  @UiField
  TableView mTable;

  public PageMissingView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PageMissingPresenter(this).handlers();

    mFsg.setExtra(true);

    mTable.addStyleName("kh_small_table");
  }

  @Override
  public void clear()
  {
    mTable.clear();
    int row = mTable.getRowCount();
    mTable.setWidget(row, 0, new Label("#"));
    mTable.setWidget(row, 1, new Label("Select"));
    mTable.setWidget(row, 2, new Label("Name"));
    mTable.setWidget(row, 3, new Label("Missing"));
    mTable.setWidget(row, 4, new Label("Fsg"));
  }

  @Override
  public void addRow(String inRow, long inId, String inName, String inMissing, String inFsgTitle)
  {
    int row = mTable.getRowCount();
    mTable.setWidget(row, 0, new Span(inRow));
    mTable.setWidget(row, 1, newCheckBox(inId));
    mTable.setWidget(row, 2, new Span(inName));
    mTable.setWidget(row, 3, new Span(inMissing));
    mTable.setWidget(row, 4, new Span(inFsgTitle));
  }

  private static CheckBox newCheckBox(long inId)
  {
    CheckBox ret = new CheckBox();
    ret.setId(String.valueOf(inId));
    ret.setStyleName("checkbox", false);
    return ret;
  }

  @Override
  public List<Long> getIds()
  {
    List<Long> ret = new ArrayList<>();
    for (int i = 0; i < mTable.getRowCount(); i++)
    {
      Widget w = mTable.getTable().getWidget(i, 1);
      if (w instanceof CheckBox)
      {
        CheckBox checkBox = (CheckBox) w;
        if (checkBox.getValue())
        {
          ret.add(NumberUtil.toLong(checkBox.getId()));
        }
      }
    }
    return ret;
  }

  @Override
  public void checkAll()
  {
    for (int i = 0; i < mTable.getRowCount(); i++)
    {
      Widget w = mTable.getTable().getWidget(i, 1);
      if (w instanceof CheckBox)
      {
        ((CheckBox) w).setValue(true);
      }
    }
  }

  @Override
  public void addRow(String inName)
  {
    int row = mTable.getRowCount();
    mTable.setWidget(row, 0, new Span(inName));
    mTable.getTable().getFlexCellFormatter().setColSpan(row, 0, 7);
  }

  @UiHandler(
  {
      "mFsg"
  })
  public void onChange(ChangeEvent inEvent)
  {
    if (mFsg.isSource(inEvent))
    {
      mPresenter.filterFsg(mFsg.getSelectedValue());
    }
  }

  @Override
  public void setSubHeading(String inText)
  {
    mTitle.setText(inText);
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
  }

  @Override
  protected void onLoad()
  {
    slimScroll();
  }
}