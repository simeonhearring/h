package h.khall.shared.command;

import java.util.ArrayList;
import java.util.List;

import h.model.shared.khall.Report;
import h.style.g.shared.command.AbstractCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("ReportSaveCommand")
public class ReportSaveCommand extends AbstractCommand
{
  private List<Report> mReports;

  ReportSaveCommand()
  {
  }

  public ReportSaveCommand(Report inReports)
  {
    mReports = new ArrayList<>();
    mReports.add(inReports);
  }

  public ReportSaveCommand(List<Report> inReports)
  {
    mReports = inReports;
  }

  public List<Report> getReports()
  {
    return mReports;
  }
}