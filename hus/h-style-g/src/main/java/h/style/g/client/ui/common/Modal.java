package h.style.g.client.ui.common;

import org.gwtbootstrap3.client.ui.ModalBody;
import org.gwtbootstrap3.client.ui.ModalFooter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;

public class Modal extends org.gwtbootstrap3.client.ui.Modal
{
  private ModalBody mBody;
  private ModalFooter mFooter;

  public void body(IsWidget... inIsWidgets)
  {
    if (mBody == null)
    {
      mBody = new ModalBody();
      add(mBody);
    }
    add(mBody, inIsWidgets);
  }

  public void footer(IsWidget... inIsWidgets)
  {
    if (mFooter == null)
    {
      mFooter = new ModalFooter();
      add(mFooter);
    }
    add(mFooter, inIsWidgets);
  }

  private void add(HasWidgets inHasWidgets, IsWidget[] inIsWidgets)
  {
    for (IsWidget widget : inIsWidgets)
    {
      inHasWidgets.add(widget.asWidget());
    }
  }
}