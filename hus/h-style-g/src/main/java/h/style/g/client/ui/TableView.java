package h.style.g.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.TableLayout;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.CallBack;
import h.style.g.client.model.HasPosition;
import h.style.g.client.model.TableDisplay;

public class TableView extends AbstractView implements TableDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, TableView>
  {
  }

  @UiField
  FlexTable mTable;

  Element mHeaderRow;

  List<Element> mHeaders;

  CallBack<Void> mSortedCallBack;

  private String mSortElementId;

  public TableView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  public void setUpHead()
  {
    mHeaders = new ArrayList<>();
    Element thead = DOM.createElement("thead");
    mHeaderRow = DOM.createTR();
    DOM.appendChild(thead, mHeaderRow);
    DOM.insertChild(mTable.getElement(), thead, 0);
  }

  public FlexTable getTable()
  {
    return mTable;
  }

  @Override
  public void clearColGroup()
  {
    NodeList<Element> inputs = mTable.getElement().getElementsByTagName("colgroup");
    for (int i = 0; i < inputs.getLength(); i++)
    {
      Element e = inputs.getItem(i);
      e.removeAllChildren();
    }
  }

  @Override
  public void setWidth(int inRow, int inColumn, String inWidth)
  {
    mTable.getFlexCellFormatter().setWidth(inRow, inColumn, inWidth);
  }

  @Override
  public void setColWidth(int inCol, String inWidth)
  {
    mTable.getColumnFormatter().setWidth(inCol, inWidth);
  }

  @Override
  public void addStyleName(int inCol, String inStyleName)
  {
    mTable.getColumnFormatter().addStyleName(inCol, inStyleName);
  }

  @Override
  public void removeStyleName(int inCol, String inStyleName)
  {
    mTable.getColumnFormatter().removeStyleName(inCol, inStyleName);
  }

  @Override
  public void setTableLayout(TableLayout inLayout)
  {
    mTable.getElement().getStyle().setTableLayout(inLayout);
  }

  @Override
  public void clear()
  {
    mTable.removeAllRows();
  }

  @Override
  public void add(IsWidget inWidget)
  {
    mTable.add(inWidget);
  }

  @Override
  public void remove(IsWidget inWidget)
  {
    mTable.remove(inWidget);
  }

  @Override
  public void remove(IsWidget inWidget, boolean inRemoveRow)
  {
    int[] ret = null;
    if (inRemoveRow)
    {
      ret = index(inWidget);
    }
    remove(inWidget);
    if (ret != null)
    {
      removeRow(ret[0]);
    }
  }

  @Override
  public void removeCell(int inRow, int inCol)
  {
    mTable.removeCell(inRow, inCol);
  }

  @Override
  public void removeRow(int inRow)
  {
    mTable.removeRow(inRow);
  }

  @Override
  public int getRowCount()
  {
    return mTable.getRowCount();
  }

  @Override
  public int getCellCount(int inRow)
  {
    return mTable.getCellCount(inRow);
  }

  @Override
  public int[] index(IsWidget inWidget)
  {
    return index(mTable, inWidget);
  }

  public static int[] index(FlexTable inTable, IsWidget inWidget)
  {
    for (int row = 0; row < inTable.getRowCount(); row++)
    {
      for (int col = 0; col < inTable.getCellCount(row); col++)
      {
        if (inTable.getWidget(row, col) == inWidget)
        {
          return new int[]
              {
                  row,
                  col
              };
        }
      }
    }
    return null;
  }

  @Override
  public void setStyle(String inStyle)
  {
    mTable.setStyleName(inStyle);
  }

  @Override
  public void setWidget(int inRow, int inCol, IsWidget inWidget)
  {
    mTable.setWidget(inRow, inCol, inWidget);
  }

  @Override
  public void setWidget(int inCol, IsWidget inWidget)
  {
    int row = getRowCount();
    setWidget(row, inCol, inWidget);
  }

  @Override
  public void setWidget(IsWidget inWidget)
  {
    int row = getRowCount();
    setWidget(row, 0, inWidget);
  }

  @Override
  public RowFormatter getRowFormatter()
  {
    return mTable.getRowFormatter();
  }

  @Override
  public void setColSpan(int inRow, int inColumn, int inColSpan)
  {
    mTable.getFlexCellFormatter().setColSpan(inRow, inColumn, inColSpan);
  }

  @Override
  public void setRowSpan(int inRow, int inColumn, int inRowSpan)
  {
    mTable.getFlexCellFormatter().setRowSpan(inRow, inColumn, inRowSpan);
  }

  @Override
  public void addColHead(String inLabel)
  {
    addColHead(inLabel, null);
  }

  @Override
  public void addColHead(String inLabel, String inStyle)
  {
    Element th = DOM.createTH();
    mHeaders.add(th);
    DOM.appendChild(mHeaderRow, th);
    th.setInnerText(inLabel);
    if (inStyle != null)
    {
      th.addClassName(inStyle);
    }
  }

  @Override
  public void setColHead(String inLabel, int inCol)
  {
    mHeaders.get(inCol).setInnerText(inLabel);
  }

  @Override
  public void setColHeadStyle(String inStyle, int inCol)
  {
    mHeaders.get(inCol).setClassName(inStyle);
  }

  @Override
  public void setSortedCallBack(CallBack<Void> inCallBack)
  {
    mSortedCallBack = inCallBack;
  }

  @Override
  public void setSortable(boolean inSortable)
  {
    if (inSortable)
    {
      mSortElementId = getElementId(mTable.getElement().getElementsByTagName("tbody").getItem(0));
      sortable(mSortElementId);
    }
    else if (mSortElementId != null)
    {
      unsortable(mSortElementId);
      mSortElementId = null;
    }
    else
    {
      mSortElementId = null;
    }
  }

  @Override
  public boolean isSortable()
  {
    return mSortElementId != null;
  }

  private native void sortable(String inId)
  /*-{
  	$wnd.$("#" + inId).sortable({
  		placeholder : "sortplaceholder"
  	});
  
  	var that = this;
  	$wnd.$("#" + inId).sortable({
  		update : function(event, ui) {
  			that.@h.style.g.client.ui.TableView::updated()();
  		}
  	});
  }-*/;

  private native void unsortable(String inId)
  /*-{
		$wnd.$("#" + inId).sortable("destroy");
  }-*/;

  private void updated()
  {
    for (int i = 0; i < mTable.getRowCount(); i++)
    {
      for (int j = 0; j < mTable.getCellCount(i); j++)
      {
        Widget widget = mTable.getWidget(i, j);
        if (widget instanceof HasPosition)
        {
          ((HasPosition) widget).setPos(i);
        }
      }
    }

    if (mSortedCallBack != null)
    {
      mSortedCallBack.onCallBack(null);
    }
  }
}