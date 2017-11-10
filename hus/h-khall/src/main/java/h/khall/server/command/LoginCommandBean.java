package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.khall.shared.model.Client;
import h.khall.shared.model.Profile;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.command.LoginCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public class LoginCommandBean extends AbstractDaoCommandBean<Dao, LoginCommand>
{
  @Override
  public RpcResponse execute(LoginCommand inCommand)
  {
    Profile data = mDao.selectProfile(inCommand.getData());
    inCommand.setData(data);

    Client client = new Client();
    data.setClient(client);

    client.setChart(mDao.selectChart(data));
    client.setMeeting(mDao.selectMonthly(data));

    return inCommand;
  }
}