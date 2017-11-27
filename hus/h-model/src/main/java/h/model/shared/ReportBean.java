package h.model.shared;

public class ReportBean
{
  private String mColumnOne;
  private String mColumnTwo;
  private String mColumnThree;
  private String mColumnFour;
  private String mColumnFive;
  private String mColumnSix;
  private String mColumnSeven;
  private String mColumnEight;
  private String mColumnNine;
  private String mColumnTen;


  public ReportBean()
  {
  }

  public ReportBean(String inDateChapters, Enum<?> inPart, String inTime, String inTalkName,
      Enum<?> inSchool, Enum<?> inStudyPoint, String inParticipants, String inStudent,
      String inAssistants, String inDate)
  {
    // mSchool = inSchool;
    // mPart = inPart;
    // mStudyPoint = inStudyPoint;

    mColumnOne = inDateChapters;
    mColumnTwo = null; // inPart.getMeeting().showHead();
    mColumnThree = inTime;
    mColumnFour = inTalkName;
    mColumnFive = null; // School.display(inSchool);
    mColumnSix = null; // StudyPoint.display(inStudyPoint);
    mColumnSeven = inParticipants;
    mColumnEight = inStudent;
    mColumnNine = inAssistants;
    mColumnTen = inDate;
  }

  public ReportBean(String inDate, Enum<?> inSchool, Enum<?> inPart, Enum<?> inStudyPoint,
      String inStudent, String inAssistants, String inNote)
  {
    // mSchool = inSchool;
    // mPart = inPart;
    // mStudyPoint = inStudyPoint;

    mColumnOne = inDate;
    mColumnTwo = inStudent;
    mColumnThree = inAssistants;
    mColumnFour = inNote;
    mColumnFive = null;
    mColumnSix = null;
    mColumnSeven = null;
    mColumnEight = null;
    mColumnNine = null;
    mColumnTen = null;
  }

  public ReportBean(Enum<?> inPart, String inDate, String inDisplay)
  {
    // mSchool = School.MAIN;
    // mPart = inPart;
    mColumnOne = inDate;
    mColumnFour = inDisplay;
  }

  public String getColumnOne()
  {
    return mColumnOne;
  }

  public void setColumnOne(String inColumnOne)
  {
    mColumnOne = inColumnOne;
  }

  public String getColumnTwo()
  {
    return mColumnTwo;
  }

  public void setColumnTwo(String inColumnTwo)
  {
    mColumnTwo = inColumnTwo;
  }

  public String getColumnThree()
  {
    return mColumnThree;
  }

  public void setColumnThree(String inColumnThree)
  {
    mColumnThree = inColumnThree;
  }

  public String getColumnFour()
  {
    return mColumnFour;
  }

  public void setColumnFour(String inColumnFour)
  {
    mColumnFour = inColumnFour;
  }

  public String getColumnFive()
  {
    return mColumnFive;
  }

  public void setColumnFive(String inColumnFive)
  {
    mColumnFive = inColumnFive;
  }

  public String getColumnSix()
  {
    return mColumnSix;
  }

  public void setColumnSix(String inColumnSix)
  {
    mColumnSix = inColumnSix;
  }

  public String getColumnSeven()
  {
    return mColumnSeven;
  }

  public void setColumnSeven(String inColumnSeven)
  {
    mColumnSeven = inColumnSeven;
  }

  public String getColumnEight()
  {
    return mColumnEight;
  }

  public void setColumnEight(String inColumnEight)
  {
    mColumnEight = inColumnEight;
  }

  public String getColumnNine()
  {
    return mColumnNine;
  }

  public void setColumnNine(String inColumnNine)
  {
    mColumnNine = inColumnNine;
  }

  public String getColumnTen()
  {
    return mColumnTen;
  }

  public void setColumnTen(String inColumnTen)
  {
    mColumnTen = inColumnTen;
  }

}