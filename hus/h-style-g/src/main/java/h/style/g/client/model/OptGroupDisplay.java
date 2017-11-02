package h.style.g.client.model;

import java.util.List;

import org.gwtbootstrap3.client.ui.constants.IconType;

public interface OptGroupDisplay
{
  String getLabel();

  IconType getType();

  List<OptionDisplay> getOptions();
}