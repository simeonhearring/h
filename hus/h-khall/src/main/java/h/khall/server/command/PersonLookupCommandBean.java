package h.khall.server.command;

import java.util.ArrayList;
import java.util.List;

import h.khall.shared.command.PersonLookupCommand;
import h.model.shared.Person;
import h.style.g.server.command.AbstractCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;
import h.tool.util.RandomUtil;

public class PersonLookupCommandBean extends AbstractCommandBean<PersonLookupCommand>
{
  private static final String[] LAST =
  {
      "Smith", "Doe", "Roe", "Henderson", "Johnson", "Walker", "Davidson", "Kent", "Parker", "Wayne"
  };
  private static final String[] FIRST =
  {
      "John", "Jane", "Paul", "Phil", "Ron", "Don", "Pete", "Josh", "Simone", "Jackie", "Bruce"
  };

  @Override
  public RpcResponse execute(PersonLookupCommand inCommand)
  {
    List<Person> data = new ArrayList<>();
    for (int i = 0; i < 50; i++)
    {
      data.add(person());
    }
    inCommand.setData(data);
    return inCommand;
  }

  private Person person()
  {
    Person ret = new Person();
    ret.setLast(RandomUtil.random(LAST));
    ret.setFirst(RandomUtil.random(FIRST));
    return ret;
  }
}