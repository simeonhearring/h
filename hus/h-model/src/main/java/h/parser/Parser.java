package h.parser;

public interface Parser<M>
{
  M fromXml(String inValue);

  String toXml(M inObj);

  void xs(Parser<M> inParser);
}