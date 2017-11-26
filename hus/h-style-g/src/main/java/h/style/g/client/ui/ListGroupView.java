package h.style.g.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.CallBack;
import h.style.g.client.model.HasPosition;
import h.style.g.client.model.ListGroupDisplay;
import h.style.g.client.model.ListGroupItemDisplay;

public class ListGroupView extends AbstractView implements ListGroupDisplay
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ListGroupView>
  {
  }

  @UiField
  ListGroup mTable;

  Map<String, PosListGroupItem> mItems;

  CallBack<String[]> mSortedCallBack;

  public ListGroupView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setElementId(mTable.getElement());
    mItems = new HashMap<>();
  }

  @Override
  public void makeSortable()
  {
    sortable(mElementId);
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
            var ids = $wnd.$("#" + inId).sortable("toArray");
            that.@h.style.g.client.ui.ListGroupView::updated([Ljava/lang/String;)(ids);
          }
        });
  }-*/;

  private void updated(String[] inOrder)
  {
    String[] send = new String[inOrder.length];
    for (int i = 0; i < inOrder.length; i++)
    {
      mItems.get(inOrder[i]).setPos(i);
      send[i] = inOrder[i];
    }
    if (mSortedCallBack != null)
    {
      mSortedCallBack.onCallBack(send);
    }
  }

  void testOrder()
  {
    for (int i = 0; i < mTable.getWidgetCount(); i++)
    {
      PosListGroupItem value = (PosListGroupItem) mTable.getWidget(i);
      FlowPanel f = (FlowPanel) value.getWidget(1);
      Label l = (Label) f.getWidget(0);
      consoleNative("text: " + l.getText() + " pos: " + value.mPos);
    }
  }

  @Override
  public void setSortedCallBack(CallBack<String[]> inCallBack)
  {
    mSortedCallBack = inCallBack;
  }

  @Override
  public void clear()
  {
    mTable.clear();
  }

  @Override
  public ListGroupItemDisplay add(IsWidget inWidget)
  {
    PosListGroupItem ret = new PosListGroupItem(inWidget);
    mTable.add(ret);
    return ret;
  }

  @Override
  public int getCount()
  {
    return mTable.getWidgetCount();
  }

  private class PosListGroupItem extends ListGroupItem implements ListGroupItemDisplay
  {
    int mPos;
    IsWidget mWidget;

    public PosListGroupItem(IsWidget inWidget)
    {
      mWidget = inWidget;
      add(mWidget);
      mItems.put(getElementId(getElement()), this);
    }

    public void setPos(int inPos)
    {
      mPos = inPos;
      if (mWidget instanceof HasPosition)
      {
        ((HasPosition) mWidget).setPos(mPos);
      }
    }
  }
}