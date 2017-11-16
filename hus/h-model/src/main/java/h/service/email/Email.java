package h.service.email;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@SuppressWarnings("serial")
public class Email implements Serializable
{
  private String mFrom;
  private Set<String> mTo;

  private EmailMessage mMessage;

  Email()
  {
  }

  public Email(String inFrom)
  {
    mTo = new HashSet<String>();
    if (isValidEmailAddress(inFrom))
    {
      mFrom = inFrom;
    }
  }

  public Email(String inFrom, String... inTo)
  {
    this(inFrom);
    addTo(inTo);
  }

  public Email(String inFrom, EmailMessage inMessage, String... inTo)
  {
    this(inFrom);
    setMessage(inMessage);
    addTo(inTo);
  }

  public void setMessage(EmailMessage inMessage)
  {
    mMessage = inMessage;
  }

  public EmailMessage getMessage()
  {
    return mMessage;
  }

  public void addTo(String... inAddress)
  {
    add(mTo, inAddress);
  }

  public int getRecipientCount()
  {
    return mTo.size();
  }

  public boolean isValid()
  {
    boolean ret = true;
    if (mFrom == null)
    {
      ret = false;
    }
    else if (getRecipientCount() <= 0)
    {
      ret = false;
    }
    return ret;
  }

  @Override
  public String toString()
  {
    return toDetail();
  }

  public String toDetail()
  {
    StringBuilder ret = new StringBuilder();
    ret.append("Recipient Count: ").append(getRecipientCount());
    ret.append(" Valid: ").append(isValid());
    ret.append("\nFrom: ").append(mFrom);
    ret.append("\nTO: ");
    for (String address : mTo)
    {
      ret.append(address).append(",");
    }
    return ret.toString();
  }

  private static boolean isValidEmailAddress(String inAddress)
  {
    return (inAddress != null) && (inAddress.indexOf("@") != -1);
  }

  private static void add(Set<String> inCollection, String[] inAddress)
  {
    for (String address : inAddress)
    {
      if (isValidEmailAddress(address))
      {
        inCollection.add(address);
      }
    }
  }

  public Address getFrom() throws AddressException
  {
    return new InternetAddress(mFrom);
  }

  public Address[] getTo() throws AddressException
  {
    if (mMessage != null)
    {
      for (String value : mMessage.getAddress())
      {
        mTo.add(value);
      }
    }
    return convert(mTo);
  }

  private static Address[] convert(Set<String> inAddress) throws AddressException
  {
    Address[] ret = null;

    if ((inAddress != null) && (inAddress.size() != 0))
    {
      ret = new Address[inAddress.size()];

      Iterator<String> iterator = inAddress.iterator();
      for (int i = 0; iterator.hasNext(); i++)
      {
        ret[i] = new InternetAddress(iterator.next());
      }
    }

    return ret;
  }

  public static Address[] convert(String... inAddress) throws AddressException
  {
    Address[] ret = null;

    if ((inAddress != null) && (inAddress.length != 0))
    {
      ret = new Address[inAddress.length];

      for (int i = 0; i < inAddress.length; i++)
      {
        ret[i] = new InternetAddress(inAddress[i]);
      }
    }

    return ret;
  }
}
