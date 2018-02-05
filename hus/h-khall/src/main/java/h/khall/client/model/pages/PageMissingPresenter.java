package h.khall.client.model.pages;

import java.util.List;

import h.khall.client.model.AbstractPresenter;
import h.khall.shared.command.ReportEmailCommand;
import h.model.shared.khall.Person;
import h.model.shared.khall.YrMo;
import h.style.g.client.ui.event.RefreshEvent;

public class PageMissingPresenter extends AbstractPresenter<PageMissingPresenter.Display>
  implements RefreshEvent.Handler
{
  public PageMissingPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PageMissingPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    Data data = new Data();
    data.mList = mClient.getPersons().getPublishers();
    data.mHeading = "Congregation";
    data.mBreakdown = true;
    add(data);
  }

  @Override
  protected void add(Data inData)
  {
    mDisplay.clearPublisher();
    for (Person value : inData.mList)
    {
      mDisplay.addPublishers(value.gName(), value.getId());
    }

    mDisplay.setSubHeading(inData.mHeading);

    mDisplay.clear();
    if (inData.mBreakdown)
    {
      mDisplay.addRow("<b><i>PUBLISHERS</i></b>");
      addMissing(mClient.convertNonPioneer(inData.mList));

      mDisplay.addRow("<b><i>REGULAR AUXILIARY</i></b>");
      addMissing(mClient.convertAuxiliary(inData.mList));

      mDisplay.addRow("<b><i>REGULAR PIONEERS</i></b>");
      addMissing(mClient.convertRegular(inData.mList));
    }
    else
    {
      addMissing(mClient.convert(inData.mList));
    }
  }

  private void addMissing(List<Long> inPubIds)
  {
    if (inPubIds.size() > 0)
    {
      int[] range = YrMo.range7mo();
      int index = 1;
      for (Long id : inPubIds)
      {
        String row = index++ + ")";
        Person person = mClient.gPerson(id);
        String name = person.gName();
        String fsgTitle = mClient.gFsgTitle(person.getFsgId());
        String missing = mClient.getReports().gMissing(range, person);

        mDisplay.addRow(row, id, name, missing, fsgTitle);
      }
    }
  }

  public void sendMissingEmail()
  {
    fire(new ReportEmailCommand(mClient.getCong().getId(), mDisplay.getIds()));
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void addRow(String inRow, long inId, String inName, String inMissing, String inFsgName);

    void clearPublisher();

    void addPublishers(String inItem, String inValue);

    void clear();

    void setSubHeading(String inText);

    void addRow(String inName);

    List<Long> getIds();

    void checkAll();
  }
}