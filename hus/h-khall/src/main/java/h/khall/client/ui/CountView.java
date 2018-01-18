package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.RadioButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.ui.event.AssignmentSavedEvent;
import h.khall.shared.command.ProfileSaveCommand;
import h.model.shared.khall.Assignments;
import h.model.shared.khall.Profile;
import h.style.g.client.ui.AbstractView;
import h.style.g.client.ui.common.Global;

public class CountView extends AbstractView
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, CountView>
  {
  }

  @UiField
  RadioButton mO1, mO2, mO3;

  public CountView()
  {
    initWidget(BINDER.createAndBindUi(this));
    active(profile().getCount());
  }

  private Profile profile()
  {
    return (Profile) Global.info().getProfile();
  }

  @UiHandler(
  {
      "mO1", "mO2", "mO3"
  })
  public void onClick(ClickEvent inEvent)
  {
    Profile profile = profile();

    Object source = inEvent.getSource();
    if (mO1.equals(source))
    {
      profile.setCount(Assignments.Count.STUDENT);
    }
    else if (mO2.equals(source))
    {
      profile.setCount(Assignments.Count.NON_STUDENT);
    }
    else if (mO3.equals(source))
    {
      profile.setCount(Assignments.Count.ALL);
    }
    fire(new ProfileSaveCommand(profile), new AssignmentSavedEvent());
  }

  private void active(Assignments.Count inCount)
  {
    switch (inCount)
    {
      case STUDENT:
        mO1.setActive(true);
        break;
      case NON_STUDENT:
        mO2.setActive(true);
        break;
      case ALL:
        mO3.setActive(true);
        break;
      default:
        break;
    }
  }
}