package h.khall.client.model.pages;

import java.util.List;
import java.util.Map;

import h.khall.client.model.AbstractPresenter;
import h.model.shared.khall.FieldServiceGroup;
import h.model.shared.khall.Person;
import h.model.shared.khall.Report.PubRange;
import h.model.shared.khall.YrMo;
import h.style.g.client.ui.event.RefreshEvent;

public class PageAnalysisPresenter extends AbstractPresenter<PageAnalysisPresenter.Display>
  implements RefreshEvent.Handler
{
  public PageAnalysisPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PageAnalysisPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    addPubList();
    addAnalysisBreakdown("Congregation", mClient.getPersons().getPublishers());
  }

  private void addAnalysis(String inSubHeading, List<Long> inPubIds)
  {
    mDisplay.setSubHeading(inSubHeading);
    addAnalysis(true, inPubIds);
  }

  private void addAnalysis(boolean inClear, List<Long> inPubIds)
  {
    if (inPubIds.size() > 0)
    {
      int yr = mProfile.gCurrentServiceYear();
      int mo = mProfile.gCurrentServiceMonth();
      List<YrMo> yml = YrMo.past(yr, mo, 24);

      mDisplay.setEnding("Ending: " + yml.get(0).getDisplay());

      if (inClear)
      {
        mDisplay.clear();
      }

      int row = 1;
      Map<Long, PubRange> ranges = mClient.getReports().gPubRanges(inPubIds, yml);
      for (Long id : inPubIds)
      {
        PubRange range = ranges.get(id);
        String studies = format(range.gBibleStudiesAvg(3), range.gBibleStudiesAvg(6));
        String rv = format(range.gReturnVisitsAvg(3), range.gReturnVisitsAvg(6));
        String hours = format(range.gHoursAvg(3), range.gHoursAvg(6));
        String video = format(range.gVideoShowingsAvg(3), range.gVideoShowingsAvg(6));
        String place = format(range.gPlacementsAvg(3), range.gPlacementsAvg(6));
        String name = mClient.gName(id);
        mDisplay.addRow(row++ + ")", name, place, video, hours, rv, studies);
      }

      PubRange range = mClient.getReports().gPubRange(inPubIds, yml);
      String studies = format(range.gBibleStudiesAvg(3), range.gBibleStudiesAvg(6));
      String rv = format(range.gReturnVisitsAvg(3), range.gReturnVisitsAvg(6));
      String hours = format(range.gHoursAvg(3), range.gHoursAvg(6));
      String video = format(range.gVideoShowingsAvg(3), range.gVideoShowingsAvg(6));
      String place = format(range.gPlacementsAvg(3), range.gPlacementsAvg(6));
      String name = "<b>GROUP TOTAL</b>";
      mDisplay.addRow("", name, place, video, hours, rv, studies);
    }
  }

  private String format(double inValueA, double inValueB)
  {
    return mDisplay.format("#.#", inValueA) + "/" + mDisplay.format("#.#", inValueB);
  }

  private void addPubList()
  {
    mDisplay.clearPublisher();
    for (Person value : mClient.getPersons().getPublishers())
    {
      mDisplay.addPublishers(value.gName(), value.getId());
    }
  }

  public void filterFsg(Integer inFsgId)
  {
    if (inFsgId.intValue() <= 0)
    {
      if (FieldServiceGroup.isElderOrServant(inFsgId))
      {
        mDisplay.clearPublisher();
        List<Person> list = mClient.getPersons().getEldersOrServants();
        for (Person value : list)
        {
          mDisplay.addPublishers(value.gName(), value.getId());
        }
        addAnalysis("Elders and Servants", mClient.convert(list));
      }
      else if (FieldServiceGroup.isPioneers(inFsgId))
      {
        mDisplay.clearPublisher();
        List<Person> list = mClient.getPersons().getRegular();
        for (Person value : list)
        {
          mDisplay.addPublishers(value.gName(), value.getId());
        }
        addAnalysis("Pioneers", mClient.convert(list));
      }
      else
      {
        addPubList();
        addAnalysisBreakdown("Congregation", mClient.getPersons().getPublishers());
      }
    }
    else
    {
      mDisplay.clearPublisher();
      String fsgName = mClient.getCong().gFsgTitle(inFsgId);
      List<Person> list = mClient.getPersons().getPubFsg(inFsgId);
      for (Person value : list)
      {
        mDisplay.addPublishers(value.gName(), value.getId());
      }
      addAnalysisBreakdown(fsgName, list);
    }
  }

  private void addAnalysisBreakdown(String inSubHeading, List<Person> inList)
  {
    mDisplay.setSubHeading(inSubHeading);
    mDisplay.clear();
    mDisplay.addRow("<b><i>PUBLISHERS</i></b>");
    addAnalysis(false, mClient.convertNonPioneer(inList));
    mDisplay.addRow("<b><i>REGULAR AUXILIARY</i></b>");
    addAnalysis(false, mClient.convertAuxiliary(inList));
    mDisplay.addRow("<b><i>REGULAR PIONEERS</i></b>");
    addAnalysis(false, mClient.convertRegular(inList));
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setEnding(String inString);

    void clearPublisher();

    void addPublishers(String inItem, String inValue);

    void clear();

    void addRow(String inRow, String inName, String inPlace, String inVideo, String inHours, String inRv, String inStudies);

    String format(String inPattern, double inValue);

    void setSubHeading(String inText);

    void addRow(String inName);
  }
}