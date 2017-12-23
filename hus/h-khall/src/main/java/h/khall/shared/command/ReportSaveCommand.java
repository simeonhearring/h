package h.khall.shared.command;

import h.model.shared.khall.Report;
import h.style.g.shared.command.AbstractCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("ReportSaveCommand")
public class ReportSaveCommand extends AbstractCommand
{
  private Report mReport;

  ReportSaveCommand()
  {
  }

  public ReportSaveCommand(Report inReport)
  {
    mReport = inReport;
  }

  public Report getReport()
  {
    return mReport;
  }
}