package h.style.g.client.model;

import org.gwtbootstrap3.client.ui.constants.ButtonDismiss;
import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.constants.ButtonType;

public interface ButtonDisplay extends IconDisplay
{
  void setLoadingText(String inLoadingText);

  ButtonSize getSize();

  void setSize(ButtonSize inType);

  ButtonType getType();

  void setBlock(boolean inBlock);

  void setType(ButtonType inType);

  void setToggle(boolean inToggle);

  boolean isToggled();

  void initElementId();

  void setDataDismiss(ButtonDismiss inDismiss);

  // HandlerRegistration addFocusHandler(FocusHandler inHandler);
  //
  // HandlerRegistration addBlurHandler(BlurHandler inHandler);
  //
  // HandlerRegistration addMouseOverHandler(MouseOverHandler inHandler);
  //
  // HandlerRegistration addMouseOutHandler(MouseOutHandler inHandler);
}