package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.ui.AbstractView;

public class ChartView extends AbstractView
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ChartView>
  {
  }

  @UiField
  CanvasElement mCanvas;

  public ChartView()
  {
    initWidget(BINDER.createAndBindUi(this));
    setElementId(mCanvas);
  }

  public String getCanvasId()
  {
    return mCanvas.getId();
  }
}