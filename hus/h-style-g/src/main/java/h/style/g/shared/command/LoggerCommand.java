package h.style.g.shared.command;

import com.google.gwt.user.client.rpc.AsyncCallback;

import h.style.g.shared.rpc.CommandName;
import h.style.g.shared.rpc.common.RpcResponse;

@SuppressWarnings("serial")
@CommandName("LoggerCommand")
public class LoggerCommand extends AbstractCommand implements AsyncCallback<RpcResponse>
{
  public enum Level
  {
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL
  }

  private Level mLevel;
  private String mMessage;
  private Throwable mThrowable;

  LoggerCommand()
  {
  }

  public LoggerCommand(Level inLevel, String inMessage)
  {
    mLevel = inLevel;
    mMessage = inMessage;
  }

  public LoggerCommand(Level inLevel, String inMessage, Throwable inThrowable)
  {
    mLevel = inLevel;
    mMessage = inMessage;
    mThrowable = inThrowable;
  }

  public Level getLevel()
  {
    return mLevel;
  }

  public String getMessage()
  {
    return mMessage;
  }

  public Throwable getThrowable()
  {
    return mThrowable;
  }

  @Override
  public void onFailure(Throwable inCaught)
  {
    // do nothing
  }

  @Override
  public void onSuccess(RpcResponse inResult)
  {
    // do nothing
  }
}