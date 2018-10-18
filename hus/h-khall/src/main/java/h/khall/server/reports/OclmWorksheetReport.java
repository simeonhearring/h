package h.khall.server.reports;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import h.model.shared.khall.Assignment;
import h.model.shared.khall.Assignments.Report;
import h.model.shared.khall.Meeting.Month;
import h.model.shared.khall.Persons;
import h.model.shared.khall.StudyPoint;

public class OclmWorksheetReport extends S_89Report implements Comparator<Report>
{
  @Override
  protected boolean isWorksheet()
  {
    return true;
  }

  @Override
  protected void addHistory(Persons inPersons, Month inMonth, Assignment inAssignment, Report inReport)
  {
    StringBuilder sb = new StringBuilder();

    if (inAssignment.gStudyPoint() != null)
    {
      inReport.setStudyPoint(StudyPoint.display3(inAssignment.gStudyPoint()));
    }

    Long participantId = inAssignment.getParticipantId();
    if (participantId != null)
    {
      List<Assignment> assignments = inMonth.gHistory(participantId);

      for (String value : Assignment.getArchive(inPersons, assignments, participantId))
      {
        sb.append(value).append("<br/>");
      }

      inReport.setHistory(sb.toString());
    }
  }

  @Override
  protected void sort(List<Report> inList)
  {
    Collections.sort(inList, this);
  }

  @Override
  public int compare(Report inO1, Report inO2)
  {
    String d1 = inO1.getDate();
    String d2 = inO2.getDate();

    int ret = d1.compareTo(d2);

    if (ret == 0)
    {
      String g1 = inO1.getHall();
      String g2 = inO2.getHall();
      ret = g1.compareTo(g2);
    }

    return ret;
  }
}