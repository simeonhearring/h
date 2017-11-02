package h.style.g.client.model;

import org.gwtbootstrap3.extras.select.client.ui.Option;

public interface SelectDisplay
{
  void setValue(String inValue);

  String getValue();

  void add(Option inOption);
}