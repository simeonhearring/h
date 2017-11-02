package h.style.g.client.model;

import org.gwtbootstrap3.client.ui.ModalSize;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

public interface PopDisplay extends IsWidget
{
  HasText getHeader();

  void addBody(IsWidget inIsWidget);

  void setShow(boolean inShow);

  boolean isShowing();

  ButtonDisplay getSave();

  ButtonDisplay getClose();

  void setHeight(String inHeight);

  void setWidth(String inWidth);

  void addStyleName(String inStyleName);

  void setSize(ModalSize inSize);
}