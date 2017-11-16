package h.service.email;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.IllegalWriteException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.apache.log4j.Logger;

import h.service.email.Messages.MyMessage;
import h.service.email.Messages.MyPart;

public class ReadEmailService
{
  private static final Logger LOGGER = Logger.getLogger(ReadEmailService.class);

  private String mHost;
  private String mUser;
  private String mPassword;

  public ReadEmailService()
  {
  }

  public ReadEmailService(String inHost, String inUser, String inPassword)
  {
    mHost = inHost;
    mUser = inUser;
    mPassword = inPassword;
  }

  public void setHost(String inHost)
  {
    mHost = inHost;
  }

  public void setUser(String inUser)
  {
    mUser = inUser;
  }

  public void setPassword(String inPassword)
  {
    mPassword = inPassword;
  }

  public Messages read()
  {
    Messages ret = new Messages();
    try
    {
      Store store = getStore();

      if (store != null)
      {
        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_WRITE);

        Message messages[] = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));

        FetchProfile fp = new FetchProfile();
        fp.add(FetchProfile.Item.ENVELOPE);
        fp.add(FetchProfile.Item.CONTENT_INFO);
        inbox.fetch(messages, fp);

        ret.setCount(inbox.getUnreadMessageCount());
        ret.setMessages(getMessages(messages));

        inbox.close(true);
        store.close();
      }
    }
    catch (NoSuchProviderException e)
    {
      e.printStackTrace();
      // System.exit(1);
      throw new RuntimeException(e);
    }
    catch (MessagingException e)
    {
      e.printStackTrace();
      // System.exit(2);
      throw new RuntimeException(e);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      // System.exit(2);
      throw new RuntimeException(e);
    }

    return ret;
  }

  private Store getStore() throws MessagingException
  {
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
    Session session = Session.getDefaultInstance(props, null);
    Store ret = null;
    try
    {
      ret = session.getStore("imaps");
      ret.connect(mHost, mUser, mPassword);
      // http://springinpractice.com/2012/04/29/fixing-pkix-path-building-issues-when-using-javamail-and-smtp
    }
    catch (MessagingException e)
    {
      LOGGER.error("Error MessagingException " + mUser + " " + mHost + " " + mPassword, e);
      System.out.println("------------------" + "Error reading " + mUser + " " + mHost + " "
          + mPassword + " " + e.getMessage());
      throw e;
    }
    catch (Exception e)
    {
      LOGGER.error("Error Exception " + mUser + " " + mHost + " " + mPassword, e);
      System.out.println("------------------" + "Error reading " + mUser + " " + mHost + " "
          + mPassword + " " + e.getMessage());
      throw e;
    }
    finally
    {
      ret = null;
    }
    return ret;
  }

  private List<MyMessage> getMessages(Message[] inMessage) throws Exception
  {
    List<MyMessage> ret = new ArrayList<>();
    for (Message value : inMessage)
    {
      ret.add(getMessage(value));
      markedRead(value);
    }
    return ret;
  }

  private void markedRead(Message inMessage)
  {
    try
    {
      inMessage.setFlags(new Flags(Flag.SEEN), true);
      inMessage.saveChanges();
    }
    catch (IllegalWriteException e)
    {
      // e.printStackTrace();
    }
    catch (MessagingException e)
    {
      e.printStackTrace();
    }
  }

  private MyMessage getMessage(Message inMessage) throws Exception
  {
    MyMessage ret = new MyMessage();

    ret.setFrom(getAddress(inMessage.getFrom()));
    ret.setTo(getAddress(inMessage.getRecipients(Message.RecipientType.TO)));
    ret.setSubject(inMessage.getSubject());
    ret.setReceivedDate(inMessage.getReceivedDate());
    ret.setContentType(inMessage.getContentType());
    addPart(ret.getParts(), inMessage);

    return ret;
  }

  private String getAddress(Address[] inAddress)
  {
    StringBuilder sb = new StringBuilder();
    if (inAddress != null)
    {
      for (Address element : inAddress)
      {
        sb.append(element.toString());
      }
    }
    return sb.toString();
  }

  private byte[] convert(InputStream inInputStream) throws IOException
  {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    int nRead;
    byte[] data = new byte[16384];

    while ((nRead = inInputStream.read(data, 0, data.length)) != -1)
    {
      buffer.write(data, 0, nRead);
    }

    buffer.flush();

    return buffer.toByteArray();
  }

  private void addPart(List<MyPart> inParts, Part inPart) throws MessagingException, IOException
  {
    if (inPart.isMimeType("text/*"))
    {
      String s = (String) inPart.getContent();
      MyPart myPart = new MyPart(inPart.getContentType(), s, inPart.isMimeType("text/html"));
      inParts.add(myPart);
    }
    else if (inPart.isMimeType("multipart/*"))
    {
      Multipart mp = (Multipart) inPart.getContent();
      for (int i = 0; i < mp.getCount(); i++)
      {
        addPart(inParts, mp.getBodyPart(i));
      }
    }
    else if (inPart.isMimeType("application/*"))
    {
      InputStream is = inPart.getInputStream();
      String name = inPart.getFileName();
      String type = inPart.getContentType();
      inParts.add(new MyPart(type, new Attachment(name, type, convert(is))));
    }
  }

  private void init()
  {
    mHost = "204.93.157.98";
    setUser("test@husoftware.net");
    setPassword("test1234");
  }

  public static void main(String args[])
  {
    ReadEmailService read = new ReadEmailService();
    read.init();
    Messages messages = read.read();
    int i = 1;
    System.out.println("Count: " + messages.getCount());
    if (messages.getMessages() != null)
    {
      for (MyMessage value : messages.getMessages())
      {
        System.out.println(i++ + ") " + value.toString());
      }
    }
    else
    {
      System.out.println("Failed");
    }
  }
}
