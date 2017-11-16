package h.khall.shared.command;

import h.model.shared.khall.Assignment;
import h.style.g.shared.command.AbstractCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("AssignmentSaveCommand")
public class AssignmentSaveCommand extends AbstractCommand
{
  private Assignment mAssignment;

  AssignmentSaveCommand()
  {
  }

  public AssignmentSaveCommand(Assignment inAssignment)
  {
    mAssignment = inAssignment;
  }

  public Assignment getAssignment()
  {
    return mAssignment;
  }
}