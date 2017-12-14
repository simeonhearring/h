package h.khall.client.model;

import java.util.List;

import h.khall.client.ui.event.PartInfoEvent;
import h.model.shared.Person.Gender;
import h.model.shared.khall.Part;
import h.model.shared.khall.PartInfo;
import h.model.shared.khall.PartInfo.Info;
import h.model.shared.khall.Person;

public class PartInfoPresenter extends AbstractPresenter<PartInfoPresenter.Display>
  implements PartInfoEvent.Handler
{
  private Part mPart;
  private Long mParticipantId;
  private Gender mGender;

  public PartInfoPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PartInfoPresenter handlers()
  {
    register(addHandler(PartInfoEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(PartInfoEvent inEvent)
  {
    if (inEvent.getPart() != null)
    {
      mPart = inEvent.getPart();
      mParticipantId = inEvent.getParticipantId();
    }
    else
    {
      mGender = inEvent.getGender();
    }
    build();
  }

  private void build()
  {
    if (mPart != null)
    {
      PartInfo pi = mClient.gPartInfo(mPart, mParticipantId, mGender);
      pi.sort();
      List<Info> info = pi.getInfo();
      String name = pi.getPart().getLabel(true);

      mDisplay.setPartName(name);
      mDisplay.clear();
      int first = 0;
      for (Info value : info)
      {
        mDisplay.add(value, first++ == 0, value.getArchive(mClient.getPersons()));
      }
    }
  }

  public void recommend()
  {
    PartInfo pi = mClient.gPartInfo(mPart, mParticipantId, mGender);
    Person rec = pi.gRecomendation();
    mDisplay.notify(rec.getName());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setPartName(String inPart);

    void add(Info inInfo, boolean inFirst, List<String> inContent);

    void clear();
  }
}