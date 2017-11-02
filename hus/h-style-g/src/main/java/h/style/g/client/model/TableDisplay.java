package h.style.g.client.model;

import com.google.gwt.dom.client.Style.TableLayout;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.google.gwt.user.client.ui.IsWidget;

public interface TableDisplay extends IsWidget
{
  void addColHead(String inLabel, String inStyle);

  void addColHead(String inLabel);

  void setRowSpan(int inRow, int inCol, int inRowSpan);

  void setColSpan(int inRow, int inCol, int inColSpan);

  RowFormatter getRowFormatter();

  void setWidget(IsWidget inWidget);

  void setWidget(int inCol, IsWidget inWidget);

  void setWidget(int inRow, int inCol, IsWidget inWidget);

  void setStyle(String inStyle);

  int getRowCount();

  int getCellCount(int inRow);

  void removeCell(int inRow, int inCol);

  void removeRow(int inRow);

  void remove(IsWidget inWidget);

  void remove(IsWidget inWidget, boolean inRemoveRow);

  int[] index(IsWidget inWidget);

  void add(IsWidget inWidget);

  void clear();

  void setColHeadStyle(String inStyle, int inCol);

  void setColHead(String inLabel, int inCol);

  void setSortedCallBack(CallBack<Void> inCallBack);

  void setSortable(boolean inSortable);

  boolean isSortable();

  void setWidth(int inRow, int inColumn, String inWidth);

  void setColWidth(int inCol, String inWidth);

  void setTableLayout(TableLayout inLayout);

  void removeStyleName(int inCol, String inStyleName);

  void addStyleName(int inCol, String inStyleName);

  void clearColGroup();
}