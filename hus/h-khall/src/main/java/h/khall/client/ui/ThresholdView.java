package h.khall.client.ui;

import org.gwtbootstrap3.extras.slider.client.ui.Slider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.shared.command.ProfileSaveCommand;
import h.model.shared.khall.Profile;
import h.style.g.client.ui.AbstractView;
import h.style.g.client.ui.common.Global;
import h.style.g.client.ui.event.RefreshEvent;


public class ThresholdView extends AbstractView
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ThresholdView>
  {
  }

  @UiField
  Slider mThreshold;

  public ThresholdView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mThreshold.setValue(profile().gThreshold());

    mThreshold.addValueChangeHandler(new ValueChangeHandler<Double>()
    {
      @Override
      public void onValueChange(ValueChangeEvent<Double> inEvent)
      {
        Profile profile = profile();
        profile.setThreshold(mThreshold.getValue());
        fire(new ProfileSaveCommand(profile), new RefreshEvent());
      }
    });
  }

  private Profile profile()
  {
    return (Profile) Global.info().getProfile();
  }
}