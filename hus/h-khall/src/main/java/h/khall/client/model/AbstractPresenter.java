package h.khall.client.model;

import java.util.List;

import h.khall.client.ui.event.ClientEvent;
import h.khall.client.ui.event.ProfileEvent;
import h.khall.shared.model.Client;
import h.model.shared.khall.Profile;
import h.style.g.client.model.Display;
import h.style.g.shared.chart.Chart.Dataset;

public class AbstractPresenter<D extends Display> extends
  h.style.g.client.model.AbstractPresenter<D> implements ProfileEvent.Handler, ClientEvent.Handler
{
  protected static String[] sMonthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

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
}