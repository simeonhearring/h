package h.style.g.client.model;

import com.google.gwt.user.client.ui.IsWidget;

public interface ListGroupDisplay extends IsWidget
{
  void clear();

  ListGroupItemDisplay add(IsWidget inWidget);

  int getCount();

  void setStyleName(String inStyle);

  void makeSortable();

  void setSortedCallBack(CallBack<String[]> inCallBack);
}