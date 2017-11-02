package h.style.g.shared.command;

import h.model.shared.SessionInfo;
import h.style.g.shared.rpc.CommandName;
import h.style.g.shared.rpc.common.WantsServerInfo;

@SuppressWarnings("serial")
@CommandName("SessionInfoCommand")
public class SessionInfoCommand extends AbstractDataCommand<SessionInfo> implements WantsServerInfo
{
  @Override
  public void setRemoteHost(String inRemoteHost)
  {
    getData().setRemoteHost(inRemoteHost);
  }

  @Override
  public void setHostName(String inHostName)
  {
    getData().setHostName(inHostName);
  }

  @Override
  public void setVersion(String inVersion)
  {
    getData().setVersion(inVersion);
  }

  @Override
  public void setEnvironment(String inEnvironment)
  {
    getData().setEnvironment(inEnvironment);
  }

  @Override
  public String getMessagePath()
  {
    return getData().getMessagePath();
  }
}