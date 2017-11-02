package h.model.shared.exception;

@SuppressWarnings("serial")
public class NotifyException extends RuntimeException
{
  private String mNotifyMessage;

  public NotifyException()
  {
    super();
  }

  public NotifyException(String inNotifyMessage)
  {
    this();
    mNotifyMessage = inNotifyMessage;
  }

  public NotifyException(Throwable inThrowable)
  {
    super(inThrowable);
  }

  public NotifyException(String inMessage, Throwable inThrowable)
  {
    super(inMessage, inThrowable);
  }

  public NotifyException(Enum<?> inEnum)
  {
  }

  public String getNotifyMessage()
  {
    return mNotifyMessage;
  }
}
