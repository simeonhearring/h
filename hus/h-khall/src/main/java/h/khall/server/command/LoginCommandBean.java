package h.khall.server.command;

import h.khall.shared.model.Charts;
import h.khall.shared.model.Client;
import h.khall.shared.model.Profile;
import h.style.g.server.command.AbstractCommandBean;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.command.LoginCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public class LoginCommandBean extends AbstractCommandBean<LoginCommand>
{
  @Override
  public RpcResponse execute(LoginCommand inCommand)
  {
    Profile data = new Profile();
    inCommand.setData(data);

    data.setUserId("simeonlhearring@gmail.com");
    data.setUserTitle("Owner");
    data.setUserName("Simeon L Hearring");

    Client client = new Client();
    data.setClient(client);

    client.setChart(sample());

    return inCommand;
  }

  private static Chart sample()
  {
    Chart ret = new Chart(Chart.Type.LINE);

    ret.setDataType(Charts.FSG);

    ret.setResponsive(true);

    ret.addLabel("January", "February", "March", "April", "May", "June", "July");

    Chart.Dataset ds1 = ret.createDataset("Example dataset1");

    ds1.setBackgroundColor("rgba(26,179,148,0.5)");
    ds1.setBorderColor("rgba(26,179,148,0.7)");
    ds1.setPointBackgroundColor("rgba(26,179,148,1)");
    ds1.setPointBorderColor("#fff");
    ds1.addData(28, 48, 40, 19, 86, 27, 90);

    Chart.Dataset ds2 = ret.createDataset("Example dataset2");

    ds2.setBackgroundColor("rgba(220,220,220,0.5)");
    ds2.setBorderColor("rgba(220,220,220,1)");
    ds2.setPointBackgroundColor("rgba(220,220,220,1)");
    ds2.setPointBorderColor("#fff");
    ds2.addData(65, 59, 80, 81, 56, 55, 40);

    return ret;
  }
}