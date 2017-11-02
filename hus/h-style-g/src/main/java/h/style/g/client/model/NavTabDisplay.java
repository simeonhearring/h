package h.style.g.client.model;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;

public interface NavTabDisplay extends IsWidget
{
  void setStyle(String inStyle);

  void addStyleName(String inStyle);

  TabDisplay getTab1();

  TabDisplay getTab2();

  TabDisplay getTab3();

  TabDisplay getTab4();

  TabDisplay getTab5();

  HasWidgets getLeft();

  HasWidgets getRight();

  void setNavVisible(boolean inVisible);
}