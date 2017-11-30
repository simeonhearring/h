package h.khall.server.reports;

import java.util.List;

import h.model.shared.khall.Assignment;
import h.model.shared.khall.Assignments.Report;
import h.model.shared.khall.Meeting.Month;
import h.model.shared.khall.Persons;
import h.model.shared.khall.StudyPoint;

public class OclmWorksheetReport extends S_89Report
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

    if (inAssignment.getStudyPoint() != null)
    {
      inReport.setStudyPoint(StudyPoint.display3(inAssignment.getStudyPoint()));
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
}