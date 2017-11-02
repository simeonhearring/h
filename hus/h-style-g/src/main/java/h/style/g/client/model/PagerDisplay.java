package h.style.g.client.model;

import com.google.gwt.event.dom.client.ClickHandler;

public interface PagerDisplay extends Display
{
  AnchorInlineDisplay getPrev();

  AnchorInlineDisplay getNext();

  void pagesClear();

  void addPage(String inPage, ClickHandler inHandler);

  void addMax(int inMax, ClickHandler inClickHandler);

  void addGoto();

  void setPageNum(String inText);

  void setMaxNum(String inText);

  void setShowing(String inText);
}