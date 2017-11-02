package h.style.g.shared.rpc.common;

import org.gwtbootstrap3.extras.notify.client.constants.NotifyType;

@SuppressWarnings("serial")
public class NotifyResponse implements RpcResponse
{
  private NotifyType mType;
  private String mNotifyMessage;

  NotifyResponse()
  {
  }

  public NotifyResponse(NotifyType inNotifyType, String inNotifyMessage)
  {
    mType = inNotifyType;
    mNotifyMessage = inNotifyMessage;
  }

  public String getNotifyMessage()
  {
    return mNotifyMessage;
  }

  public NotifyType getNotifyType()
  {
    return mType;
  }
}