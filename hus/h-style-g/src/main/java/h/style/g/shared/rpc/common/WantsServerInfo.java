package h.style.g.shared.rpc.common;

public interface WantsServerInfo
{
  void setRemoteHost(String inRemoteHost);

  void setHostName(String inHostName);

  void setVersion(String inVersion);

  void setEnvironment(String inEnvironment);

  String getMessagePath();
}