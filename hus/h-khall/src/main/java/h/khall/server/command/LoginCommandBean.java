package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.khall.shared.command.LoginCommand;
import h.khall.shared.model.Client;
import h.khall.shared.model.Login;
import h.model.shared.khall.Profile;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;

public class LoginCommandBean extends AbstractDaoCommandBean<Dao, LoginCommand>
{
  @Override
  public RpcResponse execute(LoginCommand inCommand)
  {
    Login data = new Login();
    inCommand.setData(data);

    Profile profile = mDao.selectProfile(inCommand.getProfile());
    data.setProfile(profile);

    Client client = new Client();
    data.setClient(client);

    client.setChart(mDao.selectChart(profile));

    client.setMeeting(mDao.selectMonthly(profile));

    client.setPersons(mDao.selectPersons(profile));

    return inCommand;
  }
}