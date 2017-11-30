package h.model.shared.khall;

import java.util.List;

public interface IHistory
{
  List<Assignment> gHistory(Long inPersonId);
}