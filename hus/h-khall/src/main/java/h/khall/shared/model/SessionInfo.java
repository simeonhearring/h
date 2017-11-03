package h.khall.shared.model;

@SuppressWarnings("serial")
public class SessionInfo extends h.model.shared.SessionInfo
{
  @Override
  public String getMessagePath()
  {
    String name = "h/khall/client/resource/Messages";
    return name;
  }
}