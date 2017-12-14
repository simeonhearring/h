package h.khall.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.ParticipantInfoPresenter;
import h.model.shared.khall.Part;

public class ParticipantInfoView extends AbstractView<ParticipantInfoPresenter>
  implements ParticipantInfoPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ParticipantInfoView>
  {
  }

  @UiField
  Label mName;

  @UiField
  HTMLPanel mParts;

  public ParticipantInfoView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new ParticipantInfoPresenter(this).handlers();

    addCheckBoxes();
  }

  private void addCheckBoxes()
  {
    for (final Part value : Part.values())
    {
      final CheckBox check = new CheckBox(value.getLabel(true));
      check.setId(value.name());
      check.addClickHandler(new ClickHandler()
      {
        @Override
        public void onClick(ClickEvent inEvent)
        {
          mPresenter.clicked(value, check.getValue());
        }
      });
      mParts.add(check);
    }
  }

  @Override
  public void setName(String inName)
  {
    mName.setText(inName);
  }

  @Override
  public void check(List<Part> inParts)
  {
    List<String> keys = new ArrayList<>();
    for (Part value : inParts)
    {
      keys.add(value.name());
    }

    for (int i = 0; i < mParts.getWidgetCount(); i++)
    {
      CheckBox check = (CheckBox) mParts.getWidget(i);
      check.setValue(keys.contains(check.getId()));
    }
  }
}