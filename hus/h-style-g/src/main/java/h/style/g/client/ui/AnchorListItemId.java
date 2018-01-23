package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.AnchorListItem;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.Event;

public class AnchorListItemId extends AnchorListItem
{
  public AnchorListItemId()
  {
  }

  public AnchorListItemId(String inItem, String inId)
  {
    super(inItem);
    setId(inId);
  }

  public AnchorListItemId(String inItem, String inId, ClickHandler inHandler)
  {
    super(inItem);
    setId(inId);
    addClickHandler(inHandler);
  }

  public boolean isSource(Event<?> inEvent)
  {
    Widget source = ((Widget) inEvent.getSource()).getParent();
    return this.equals(source);
  }

  @Override
  public String getId()
  {
    return super.getId();
  }
}