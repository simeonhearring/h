package h.khall.client.model;

import java.util.List;

import h.khall.client.ui.event.ClientEvent;
import h.khall.client.ui.event.ProfileEvent;
import h.khall.shared.model.Client;
import h.model.shared.khall.FieldServiceGroup;
import h.model.shared.khall.Person;
import h.model.shared.khall.Profile;
import h.model.shared.khall.Profile.Security;
import h.style.g.client.model.Display;
import h.style.g.client.ui.common.Global;
import h.style.g.shared.chart.Chart.Dataset;

public class AbstractPresenter<D extends Display> extends
  h.style.g.client.model.AbstractPresenter<D> implements ProfileEvent.Handler, ClientEvent.Handler
{
  protected Profile mProfile;
  protected Client mClient;

  public AbstractPresenter()
  {
    register(addHandler(ProfileEvent.TYPE, this));
    register(addHandler(ClientEvent.TYPE, this));
  }

  @Override
  public void dispatch(ProfileEvent inEvent)
  {
    mProfile = (Profile) inEvent.getProfile();
  }

  @Override
  public void dispatch(ClientEvent inEvent)
  {
    mClient = (Client) inEvent.getClient();
  }

  boolean isEdit(Security inSecurity)
  {
    return ((Profile) Global.profile()).isEdit(inSecurity);
  }

  public static void format(Dataset inSet, int inColor)
  {
    // http://standardista.com/webkit/ch7/hsla.html
    String c1 = "hsla(" + inColor + ",67%,51%,";
    inSet.setBorderColor(c1 + "0." + 9 + ")");
    // inSet.setBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    // inSet.setPointBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setPointBorderColor("#fff");
  }

  public static <T extends Enum<?>> void update(Boolean inChecked, List<T> inList, T inValue)
  {
    if (inChecked)
    {
      if (!inList.contains(inValue))
      {
        inList.add(inValue);
      }
    }
    else
    {
      inList.remove(inValue);
    }
  }

  public static class Data
  {
    public List<Person> mList = null;
    public String mHeading = null;
    public boolean mBreakdown = false;
  }

  public final void filterFsg(Integer inFsgId)
  {
    Data data = new Data();

    if (mClient.getCong().getFsgs().containsKey(inFsgId))
    {
      data.mBreakdown = true;
      data.mHeading = mClient.getCong().gFsgTitle(inFsgId);
      data.mList = mClient.getPersons().getPubFsg(inFsgId);
    }
    else if (FieldServiceGroup.isElderOrServant(inFsgId))
    {
      data.mBreakdown = false;
      data.mHeading = "Elders and Servants";
      data.mList = mClient.getPersons().getEldersOrServants();
    }
    else if (FieldServiceGroup.isPioneers(inFsgId))
    {
      data.mBreakdown = false;
      data.mHeading = "Pioneers";
      data.mList = mClient.getPersons().getRegular();
    }
    else
    {
      data.mBreakdown = true;
      data.mHeading = "Congregation";
      data.mList = mClient.getPersons().getPublishers();
    }

    add(data);
  }

  protected void add(Data inData)
  {
  }
}