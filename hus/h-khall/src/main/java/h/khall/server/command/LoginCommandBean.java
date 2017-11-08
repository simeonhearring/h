package h.khall.server.command;

import h.khall.shared.model.Charts;
import h.khall.shared.model.Client;
import h.khall.shared.model.Profile;
import h.style.g.server.command.AbstractCommandBean;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.command.LoginCommand;
import h.style.g.shared.rpc.common.RpcResponse;
import h.tool.util.RandomUtil;

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

    String[] months =
      {
          "January", "February", "March", "April", "May", "June", "July"
      };
    ret.addLabel(months);


    dataset(ret.createDataset("Hearring"), random(months.length));
    dataset(ret.createDataset("Humphrey"), random(months.length));
    dataset(ret.createDataset("Tunstill"), random(months.length));
    dataset(ret.createDataset("Kerstner"), random(months.length));

    return ret;
  }

  private static void dataset(Chart.Dataset inSet, double[] inData)
  {
    String c1 = "hsla(" + RandomUtil.randomInt(360) + ",90%,90%,";
    inSet.setBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setBorderColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setPointBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setPointBorderColor("#fff");
    inSet.addData(inData);
  }

  private static double[] random(int inLength)
  {
    double[] ret = new double[inLength];
    for (int i = 0; i < ret.length; i++)
    {
      ret[i] = RandomUtil.randomInt(100);
    }
    return ret;
  }
}