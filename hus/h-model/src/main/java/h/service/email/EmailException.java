package h.service.email;

@SuppressWarnings("serial")
public class EmailException extends RuntimeException
{
  public EmailException(String inString, Throwable inE)
  {
    super(inString, inE);
  }

  public EmailException(String inString)
  {
    super(inString);
  }
}
