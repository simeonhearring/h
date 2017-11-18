package h.model.shared.khall;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Curriculums implements Serializable
{
  private List<Curriculum> mCurriculums;

  public List<Curriculum> getCurriculums()
  {
    return mCurriculums;
  }

  public void setCurriculums(List<Curriculum> inCurriculums)
  {
    mCurriculums = inCurriculums;
  }
}