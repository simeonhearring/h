package h.style.g.client.model;

import com.google.gwt.user.client.ui.IsWidget;

public interface DndDelegate<I extends IsWidget>
{
  void add(I inItem);

  void remove(I inItem);

  void removeAll();

  void onDragStart(int inPos, I inItem);

  void onDragOver(int inPos, I inItem);

  void onDragLeave(int inPos, I inItem);

  void onDrop(int inPos, I inItem);

  void onMouseMove(int inPos, I inItem);
}