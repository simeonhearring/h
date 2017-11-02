package h.style.g.client.model;

import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.IsWidget;

public interface TextAreaDisplay extends TakesValue<String>, IsWidget, TextBoxDisplay
{
  public enum Resize
  {
    NONE,
    VERTICAL,
    HORIZONTAL,
    BOTH;
  }

  void setVisibleLines(int inLines);

  void elastic();

  void elastic(int inDelay);

  void setResize(Resize inResize);
}