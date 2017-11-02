package h.style.g.client.model;

public interface Presenter<D extends Display>
{
  void go(D inDisplay);

  void initDisplay(D inDisplay);
}