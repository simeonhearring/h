package h.style.g.client.model;

import com.google.gwt.event.dom.client.ClickHandler;

public interface NoticeDisplay extends Display
{
  void addPreviousClickHandler(ClickHandler inHandler);

  void addNextClickHandler(ClickHandler inHandler);

  LabelDisplay getMessage();

  void setRead(boolean inRead);

  void setPagerVisible(boolean inVisible);

  void setHeading(String inHeading);

  ModalDisplay getModal();
}