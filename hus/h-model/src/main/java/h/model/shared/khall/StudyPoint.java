package h.model.shared.khall;

import java.util.List;

import h.model.shared.Tag;
import h.model.shared.util.EnumUtil;

public enum StudyPoint implements Tag
{
  SP_1(1, "Accurate Reading", Type.READ, Type.DEMO, Type.TALK),
  SP_2(2, "Words Clearly Spoken", Type.READ, Type.DEMO, Type.TALK),
  SP_3(3, "Correct Pronunciation", Type.READ, Type.DEMO, Type.TALK),
  SP_4(4, "Fluent Delivery", Type.READ, Type.DEMO, Type.TALK),
  SP_5(5, "Appropriate Pausing", Type.READ, Type.DEMO, Type.TALK),
  SP_6(6, "Proper Sense Stress", Type.READ, Type.DEMO, Type.TALK),
  SP_7(7, "Principal Ideas Emphasized", Type.READ),
  SP_8(8, "Suitable Volume", Type.READ, Type.DEMO, Type.TALK),
  SP_9(9, "Modulation", Type.READ, Type.DEMO, Type.TALK),
  SP_10(10, "Enthusiasm", Type.READ, Type.DEMO, Type.TALK),
  SP_11(11, "Warmth and Feeling", Type.READ, Type.DEMO, Type.TALK),
  SP_12(12, "Gestures and Facial Expressions", Type.READ, Type.DEMO, Type.TALK),
  SP_13(13, "Visual Contact", Type.READ, Type.DEMO, Type.TALK),
  SP_14(14, "Naturalness", Type.READ, Type.DEMO, Type.TALK),
  SP_15(15, "Good Personal Appearance", Type.READ, Type.DEMO, Type.TALK),
  SP_16(16, "Poise", Type.READ, Type.DEMO, Type.TALK),
  SP_17(17, "Use of Microphone", Type.READ, Type.DEMO, Type.TALK),
  SP_18(18, "Use of Bible in Replying", Type.DEMO),
  SP_19(19, "Use of Bible Encouraged", Type.DEMO, Type.TALK),
  SP_20(20, "Scriptures Effectively Introduced", Type.DEMO, Type.TALK),
  SP_21(21, "Scriptures Read With Proper Emphasis", Type.DEMO, Type.TALK),
  SP_22(22, "Scriptures Correctly Applied", Type.DEMO, Type.TALK),
  SP_23(23, "Practical Value Made Clear", Type.DEMO, Type.TALK),
  SP_24(24, "Choice of Words", Type.DEMO, Type.TALK),
  SP_25(25, "Use of an Outline", Type.DEMO, Type.TALK),
  SP_26(26, "Logical Development of Material", Type.DEMO, Type.TALK),
  SP_27(27, "Extemporaneous Delivery", Type.DEMO, Type.TALK),
  SP_28(28, "Conversational Manner", Type.DEMO, Type.TALK),
  SP_29(29, "Voice Quality", Type.DEMO, Type.TALK),
  SP_30(30, "Interest Shown in the Other Person", Type.DEMO),
  SP_31(31, "Respect Shown to Others", Type.DEMO, Type.TALK),
  SP_32(32, "Expressed With Conviction", Type.DEMO, Type.TALK),
  SP_33(33, "Tactful yet Firm", Type.DEMO, Type.TALK),
  SP_34(34, "Upbuilding and Positive", Type.DEMO, Type.TALK),
  SP_35(35, "Repetition for Emphasis", Type.DEMO, Type.TALK),
  SP_36(36, "Theme Developed", Type.DEMO, Type.TALK),
  SP_37(37, "Main Points Made to Stand Out", Type.DEMO, Type.TALK),
  SP_38(38, "Interest-Arousing Introduction", Type.DEMO, Type.TALK),
  SP_39(39, "Effective Conclusion", Type.DEMO, Type.TALK),
  SP_40(40, "Accuracy of Statement", Type.DEMO, Type.TALK),
  SP_41(41, "Understandable to Others", Type.DEMO, Type.TALK),
  SP_42(42, "Informative to Your Audience", Type.DEMO, Type.TALK),
  SP_43(43, "Use of Assigned Material", Type.DEMO, Type.TALK),
  SP_44(44, "Effective Use of Questions", Type.DEMO, Type.TALK),
  SP_45(45, "Illustrations/Examples That Teach", Type.DEMO, Type.TALK),
  SP_46(46, "Illustrations From Familiar Situations", Type.DEMO, Type.TALK),
  SP_47(47, "Effective Use of Visual Aids", Type.DEMO, Type.TALK),
  SP_48(48, "Reasoning Manner", Type.DEMO, Type.TALK),
  SP_49(49, "Sound Arguments Given", Type.DEMO, Type.TALK),
  SP_50(50, "Effort to Reach the Heart", Type.DEMO, Type.TALK),
  SP_51(51, "Accurately Timed, Properly Proportioned", Type.DEMO, Type.TALK),
  SP_52(52, "Effective Exhortation", Type.TALK),
  SP_53(53, "Audience Encouraged and Strengthened", Type.TALK);

  private int mId;
  private String mName;
  private Type[] mTypes;

  private StudyPoint(int inId, String inName, Type... inTypes)
  {
    mId = inId;
    mName = inName;
    mTypes = inTypes;
  }

  @Override
  public String getId()
  {
    return String.valueOf(mId);
  }

  enum Type
  {
    READ,
    DEMO,
    TALK;
  }

  private boolean contains(Type... inType)
  {
    boolean ret = false;

    for (Type value : mTypes)
    {
      for (Type value1 : inType)
      {
        ret |= value1 == value;
      }
    }

    return ret;
  }

  public static String display(StudyPoint inStudyPoint)
  {
    return inStudyPoint == null ? "" : "(" + inStudyPoint.mId + ")";
  }

  public static String display2(StudyPoint inStudyPoint)
  {
    return inStudyPoint != null ? "#" + inStudyPoint.getId() : "";
  }

  public boolean isValid(Part inPart)
  {
    boolean ret = false;

    switch (inPart)
    {
      case BIBLE_READING:
        ret = contains(Type.READ);
        break;
      case INITIAL_CALL:
      case F_RETURN_VISIT:
      case S_RETURN_VISIT:
      case T_RETURN_VISIT:
      case BIBLE_STUDY:
        ret = contains(Type.DEMO);
        break;
      case TALK:
        ret = contains(Type.TALK);
        break;
      default:
        break;
    }

    return ret;
  }

  @Override
  public String getType()
  {
    return "COUNSEL";
  }

  @Override
  public String getValue()
  {
    return getId();
  }

  @Override
  public String getName()
  {
    return mName;
  }

  public String getQueryInfo()
  {
    return mId + " " + mName.toLowerCase();
  }

  @Override
  public String toString()
  {
    return getQueryInfo();
  }

  public static boolean doFilter(String inQuery)
  {
    return startsWith(inQuery, "#", "1", "2", "3", "4", "5", "6", "7", "8", "9");
  }

  private static boolean startsWith(String inQuery, String... inText)
  {
    boolean ret = false;
    for (String value : inText)
    {
      ret |= inQuery.startsWith(value);
    }
    return ret;
  }

  public static void filter(List<Tag> inData, Part inPart, String inQuery)
  {
    inQuery = inQuery.replaceAll("#", "").toLowerCase();
    for (StudyPoint value : StudyPoint.values())
    {
      if (value.getQueryInfo().contains(inQuery))
      {
        if (inPart == null || value.isValid(inPart))
        {
          inData.add(value);
        }
      }
    }
  }

  public static StudyPoint get(String inName)
  {
    return EnumUtil.valueOf(inName, values());
  }

  public static String get(StudyPoint inValue)
  {
    return EnumUtil.name(inValue);
  }
}