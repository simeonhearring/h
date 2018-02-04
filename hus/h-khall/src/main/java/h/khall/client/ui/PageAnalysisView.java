package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.html.OrderedList;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.pages.PageAnalysisPresenter;
import h.style.g.client.ui.TableView;

public class PageAnalysisView extends AbstractView<PageAnalysisPresenter>
  implements PageAnalysisPresenter.Display, ClickHandler
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PageAnalysisView>
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

  public PageAnalysisView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PageAnalysisPresenter(this).handlers();

    // mHeading.getElement().getStyle().setProperty("pageBreakBefore",
    // "always");

    mFsg.setExtra(true);

    mTable.addStyleName("kh_small_table");
  }

  @Override
  public String format(String inPattern, double inValue)
  {
    return NumberFormat.getFormat(inPattern).format(inValue);
  }

  @Override
  public void clear()
  {
    mTable.clear();
    int row = mTable.getRowCount();
    mTable.setWidget(row, 0, new Label("#"));
    mTable.setWidget(row, 1, new Label("Name"));
    mTable.setWidget(row, 2, new Label("Place"));
    mTable.setWidget(row, 3, new Label("Video"));
    mTable.setWidget(row, 4, new Label("Hours"));
    mTable.setWidget(row, 5, new Label("RV"));
    mTable.setWidget(row, 6, new Label("Studies"));
  }

  @Override
  public void addRow(String inRow, String inName, String inPlace, String inVideo, String inHours, String inRv, String inStudies)
  {
    int row = mTable.getRowCount();
    mTable.setWidget(row, 0, new Span(inRow));
    mTable.setWidget(row, 1, new Span(inName));
    mTable.setWidget(row, 2, new Span(inPlace));
    mTable.setWidget(row, 3, new Span(inVideo));
    mTable.setWidget(row, 4, new Span(inHours));
    mTable.setWidget(row, 5, new Span(inRv));
    mTable.setWidget(row, 6, new Span(inStudies));

    mTable.getTable().getCellFormatter().addStyleName(row, 2, "kh_table_right");
    mTable.getTable().getCellFormatter().addStyleName(row, 3, "kh_table_right");
    mTable.getTable().getCellFormatter().addStyleName(row, 4, "kh_table_right");
    mTable.getTable().getCellFormatter().addStyleName(row, 5, "kh_table_right");
    mTable.getTable().getCellFormatter().addStyleName(row, 6, "kh_table_right");
  }

  @Override
  public void addRow(String inName)
  {
    int row = mTable.getRowCount();
    mTable.setWidget(row, 0, new Span(inName));
    mTable.getTable().getFlexCellFormatter().setColSpan(row, 0, 7);
  }

  @Override
  public void setEnding(String inText)
  {
    mHeading.setSubText(inText);
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