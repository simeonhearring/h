package h.khall.client.model.pages;

import java.util.List;
import java.util.Map;

import h.khall.client.model.AbstractPresenter;
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
      mDisplay.addPublishers(value.gName(), value.gId());
    }

    mDisplay.setSubHeading(inData.mHeading);

    mDisplay.clear();
    if (inData.mBreakdown)
    {
      mDisplay.addRow("<b><i>PUBLISHERS</i></b>");
      addAnalysis(mClient.convertNonPioneer(inData.mList));

      mDisplay.addRow("<b><i>REGULAR AUXILIARY</i></b>");
      addAnalysis(mClient.convertAuxiliary(inData.mList));

      mDisplay.addRow("<b><i>REGULAR PIONEERS</i></b>");
      addAnalysis(mClient.convertRegular(inData.mList));
    }
    else
    {
      addAnalysis(mClient.convert(inData.mList));
    }
  }

  private void addAnalysis(List<Long> inPubIds)
  {
    if (inPubIds.size() > 0)
    {
      int yr = mProfile.gCurrentServiceYear(); // TODO
      int mo = mProfile.gCurrentServiceMonth();
      List<YrMo> yml = YrMo.past(yr, mo, 24);

      mDisplay.setEnding("Ending: " + yml.get(0).getDisplay());

      int index = 1;
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
        String row = index++ + ")";
        mDisplay.addRow(row, name, place, video, hours, rv, studies);
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