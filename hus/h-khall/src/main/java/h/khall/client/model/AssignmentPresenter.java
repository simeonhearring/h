package h.khall.client.model;

import java.util.List;

import h.khall.client.model.AssignmentPresenter.AssignDisplay;
import h.model.shared.Part;
import h.model.shared.Tag;
import h.style.g.client.model.CallBack;

public class AssignmentPresenter extends AbstractPresenter<AssignmentPresenter.Display>
  implements CallBack<AssignDisplay>
{
  public AssignmentPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  @Override
  public void onCallBack(AssignDisplay inDisplay)
  {
    inDisplay.remove(inDisplay.getValues().get(0));
    for (Tag value : inDisplay.getValues())
    {
      mDisplay.notify("Callback: " + value.getName());
    }
  }

  public interface AssignDisplay
  {
    Part getPart();

    List<Tag> getValues();

    void remove(Tag inTag);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}