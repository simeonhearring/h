package h.style.g.client.model;

import com.google.gwt.user.client.ui.IsWidget;

public class DndDelegateNoop<I extends IsWidget> implements DndDelegate<I>
{
  @Override
  public void add(I inItem)
  {
  }

  @Override
  public void remove(I inItem)
  {
  }

  @Override
  public void removeAll()
  {
  }

  @Override
  public void onDragStart(int inPos, I inItem)
  {
  }

  @Override
  public void onDragOver(int inPos, I inItem)
  {
  }

  @Override
  public void onDragLeave(int inPos, I inItem)
  {
  }

  @Override
  public void onDrop(int inPos, I inItem)
  {
  }

  @Override
  public void onMouseMove(int inPos, I inItem)
  {
  }
}