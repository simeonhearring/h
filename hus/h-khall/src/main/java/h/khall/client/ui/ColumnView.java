package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.ui.AbstractView;

public class ColumnView extends AbstractView
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ColumnView>
  {
  }

  public ColumnView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }
}