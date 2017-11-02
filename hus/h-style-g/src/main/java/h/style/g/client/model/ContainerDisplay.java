package h.style.g.client.model;

import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;

public interface ContainerDisplay extends IsWidget
{
  <T extends HasWidgets & HasVisibility> T getHead();

  <T extends HasWidgets & HasVisibility> T getBody();

  NavTabDisplay addNavTab();

  <T extends HasWidgets & HasVisibility> T getFoot();
}