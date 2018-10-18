package h.khall.server.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import h.model.shared.khall.Assignment;
import h.model.shared.khall.Assignments.Report;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Meeting.Month;
import h.model.shared.khall.Persons;
import h.model.shared.khall.StudyPoint;

public class S_89Report extends OclmReport
{
  @Override
  protected boolean isStudent()
  {
    return true;
  }

  @Override
  protected void addReports(List<Report> inList, Persons inPersons, Month inMonth,
      Map<Hall, Assignment> inAssignments, Report inReport, String[] inChairman)
  {
    List<Hall> list = new ArrayList<>(inAssignments.keySet());
    Collections.sort(list);

    for (Hall value : list)
    {
      Assignment assignment = inAssignments.get(value);

      if (assignment.isParticipant())
      {
        Report report = inReport.copy();
        report.setHall(value.name());

        String studyPoint = StudyPoint.display2(assignment.gStudyPoint());
        String[] assigns = assign(inPersons, assignment, inChairman);
        report.setParticipantsA(assigns[0], assigns[1], studyPoint);

        addHistory(inPersons, inMonth, assignment, report);

        inList.add(report);
      }
    }
  }

  protected void addHistory(Persons inPersons, Month inMonth, Assignment inAssignment, Report inReport)
  {
  }
}