package h.service.email;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import h.service.email.Messages.MyMessage;

public class Messages implements Comparator<MyMessage>
{
  public enum Type
  {
    TEXT("text/plain"),
    HTML("text/html");

    private final String mType;

    private Type(String inType)
    {
      mType = inType;
    }

    public String getType()
    {
      return mType;
    }
  }

  private int mCount;
  private List<MyMessage> mMessages;

  public int getCount()
  {
    return mCount;
  }

  public void setCount(int inCount)
  {
    mCount = inCount;
  }

  public List<MyMessage> getMessages()
  {
    return mMessages;
  }

  public void setMessages(List<MyMessage> inMessages)
  {
    mMessages = inMessages;
  }

  @Override
  public int compare(MyMessage inObj1, MyMessage inObj2)
  {
    return inObj1.getReceivedDate().compareTo(inObj2.getReceivedDate());
  }

  public void sort()
  {
    Collections.sort(mMessages, this);
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("MyMessages [mCount=");
    builder.append(mCount);
    builder.append(", mMessages=");
    for (MyMessage value : mMessages)
    {
      builder.append(value);
    }
    builder.append("]");
    return builder.toString();
  }

  public static class MyMessage
  {
    private String mFrom;
    private String mTo;
    private String mSubject;
    private Date mReceivedDate;
    private String mContentType;
    private List<MyPart> mParts = new ArrayList<>();

    public void addPart(MyPart inPart)
    {
      mParts.add(inPart);
    }

    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      builder.append("MyMessage [mFrom=");
      builder.append(mFrom);
      builder.append(", mTo=");
      builder.append(mTo);
      builder.append(", mSubject=");
      builder.append(mSubject);
      builder.append(", mReceivedDate=");
      builder.append(mReceivedDate);
      builder.append(", mContentType=");
      builder.append(mContentType);
      builder.append(", mParts=");
      for (MyPart value : mParts)
      {
        builder.append(value);
      }
      builder.append("]");
      return builder.toString();
    }

    public String getFrom()
    {
      return mFrom;
    }

    public String getPureFrom()
    {
      String ret = pure(mFrom);
      return ret;
    }

    public void setFrom(String inFrom)
    {
      mFrom = inFrom;
    }

    private String pure(String inText)
    {
      String ret = inText;

      int s = ret.indexOf("<");
      int e = ret.indexOf(">");
      if (s != -1 && e != -1)
      {
        ret = ret.substring(s + 1, e);
      }
      return ret;
    }

    public String getTo()
    {
      return mTo;
    }

    public String getPureTo()
    {
      String ret = pure(mTo);
      return ret;
    }

    public void setTo(String inTo)
    {
      mTo = inTo;
    }

    public String getSubject()
    {
      return mSubject;
    }

    public void setSubject(String inSubject)
    {
      mSubject = inSubject;
    }

    public Date getReceivedDate()
    {
      return mReceivedDate;
    }

    public void setReceivedDate(Date inReceivedDate)
    {
      mReceivedDate = inReceivedDate;
    }

    public String getContentType()
    {
      return mContentType;
    }

    public void setContentType(String inContentType)
    {
      mContentType = inContentType;
    }

    public List<MyPart> getParts()
    {
      return mParts;
    }

    public void setParts(List<MyPart> inParts)
    {
      mParts = inParts;
    }

    public String getMessage(Type inType)
    {
      return getMessage(inType.getType());
    }

    public String getMessage(String inType)
    {
      String ret = null;
      for (MyPart value : mParts)
      {
        if (value.getType().indexOf(inType) != -1)
        {
          ret = value.getMessage();
          break;
        }
      }
      return ret;
    }

    public String getMessageNoNewLines(String inType)
    {
      String ret = getMessage(inType);
      return ret != null ? ret.replaceAll("\n", " ").replaceAll("\r", " ").trim() : ret;
    }

    public String getInfo()
    {
      return toString();
    }
  }

  public static class MyPart
  {
    private String mType;
    private String mMessage;
    private boolean mHtml;
    private Attachment mAttachment;

    MyPart()
    {
    }

    public MyPart(String inType, Attachment inAttachment)
    {
      mType = inType;
      mAttachment = inAttachment;
    }

    public MyPart(String inType, String inMessage)
    {
      this(inType, inMessage, false);
    }

    public MyPart(String inType, String inMessage, boolean inHtml)
    {
      mType = inType;
      mHtml = inHtml;
      mMessage = inMessage;
    }

    public boolean isHtml()
    {
      return mHtml;
    }

    public String getType()
    {
      return mType;
    }

    public String getMessage()
    {
      return mMessage;
    }

    public Attachment getAttachment()
    {
      return mAttachment;
    }

    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      builder.append("MyPart [mType=");
      builder.append(mType);
      builder.append(", mHtml=");
      builder.append(mHtml);
      builder.append(", mMessage=");
      builder.append(mMessage);
      builder.append("]");
      return builder.toString();
    }
  }
}