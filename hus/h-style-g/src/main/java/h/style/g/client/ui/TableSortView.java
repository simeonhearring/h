package h.style.g.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.CallBack;
import h.style.g.client.model.HasPosition;

public class TableSortView extends AbstractView
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, TableSortView>
  {
  }

  @UiField
  FlexTable mTable;

  CallBack<Void> mSortedCallBack;

  public TableSortView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

  public void setSortedCallBack(CallBack<Void> inCallBack)
  {
    mSortedCallBack = inCallBack;
  }

  public void makeSortable()
  {
    sortable(getElementId(mTable.getElement().getElementsByTagName("tbody").getItem(0)));
  }

  private native void sortable(String inId)
  /*-{
        $wnd.$("#" + inId).sortable(
        {
          placeholder : "sortplaceholder"
        });

        var that = this;
        $wnd.$("#" + inId).sortable(
        {
          update : function(event, ui)
          {
            that.@vanderbilt.mc.style.client.ui.TableSortView::updated()();
          }
        });
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

  public void clear()
  {
    mTable.clear();
  }

  public void add(IsWidget inWidget)
  {
    mTable.setWidget(mTable.getRowCount(), 0, inWidget);
  }

  public int getCount()
  {
    return mTable.getRowCount();
  }
}