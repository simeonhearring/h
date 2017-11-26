package h.style.g.client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.DomEvent.Type;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import h.style.g.client.model.DndDelegate;
import h.style.g.client.model.DndDelegateNoop;
import h.style.g.client.model.HasPosition;

public class DndController<I extends IsWidget> extends DndDelegateNoop<I>
{
  private static String sOnDragOverStyle = "ondragover";

  private ArrayList<DndItemWrapper<I>> mItems = new ArrayList<>();

  private ArrayList<Type<?>> mWatch;
  private HashMap<Type<?>, List<EventHandler>> mHandlers;

  private int mDraggingPos;
  private boolean mDescending;

  private DndDelegate<I> mDelegate;

  private boolean mOnMouseMoveAttribute, mOnDropAttribute;

  public DndController()
  {
    mDelegate = this;
    init(MouseMoveEvent.getType(), DragStartEvent.getType(), DragOverEvent.getType(),
        DragLeaveEvent.getType(), DropEvent.getType());
  }

  private void init(Type<?>... inTypes)
  {
    mWatch = new ArrayList<>();
    mHandlers = new HashMap<>();
    for (Type<?> value : inTypes)
    {
      mWatch.add(value);
      mHandlers.put(value, new ArrayList<EventHandler>());
    }
  }

  protected ArrayList<DndItemWrapper<I>> getItems()
  {
    return mItems;
  }

  public void addMouseMoveHandler(MouseMoveHandler inHandler)
  {
    mHandlers.get(MouseMoveEvent.getType()).add(inHandler);
  }

  public void addDragStartHandler(DragStartHandler inHandler)
  {
    mHandlers.get(DragStartEvent.getType()).add(inHandler);
  }

  public void addDragOverHandler(DragOverHandler inHandler)
  {
    mHandlers.get(DragOverEvent.getType()).add(inHandler);
  }

  public void addDragLeaveHandler(DragLeaveHandler inHandler)
  {
    mHandlers.get(DragLeaveEvent.getType()).add(inHandler);
  }

  public void addDropHandler(DropHandler inHandler)
  {
    mHandlers.get(DropEvent.getType()).add(inHandler);
  }

  public ArrayList<Type<?>> getWatch()
  {
    return mWatch;
  }

  public final void setDelegate(DndDelegate<I> inDelegate)
  {
    mDelegate = inDelegate;
  }

  public final void setOnDragOverStyle(String inOnDragOver)
  {
    sOnDragOverStyle = inOnDragOver;
  }

  public final void setDescending(boolean inDescending)
  {
    mDescending = inDescending;
    reorderAndAdd();
  }

  public final boolean isDescending()
  {
    return mDescending;
  }

  public final void putIn(List<I> inItems)
  {
    cleanUp();
    for (I value : inItems)
    {
      putIn(value);
    }
  }

  public final void putIn(I inItem)
  {
    putIn(mItems.size(), inItem);
  }

  public final void putIn(int inPos, I inItem)
  {
    mItems.add(create(inPos, inItem));

    addAttribute(inItem, inPos);

    mDelegate.add(inItem);
  }

  public final void takeOut(I inItem, boolean inReBuild)
  {
    if (inItem != null)
    {
      DndItemWrapper<I> remove = null;
      for (DndItemWrapper<I> value : mItems)
      {
        if (inItem.equals(value.getItem()))
        {
          remove = value;
          break;
        }
      }
      if (remove != null)
      {
        mItems.remove(remove);
      }
    }
    if (inReBuild)
    {
      reorderAndAdd();
    }
    mDelegate.remove(inItem);
  }

  public final void cleanUp()
  {
    reset();
    mDelegate.removeAll();
  }

  public final void reset()
  {
    mItems.clear();
  }

  public final void setOnMouseMoveAttribute(boolean inOnMouseMoveAttribute)
  {
    mOnMouseMoveAttribute = inOnMouseMoveAttribute;
    if (mOnMouseMoveAttribute)
    {
      exportOnMouseMove();
    }
  }

  public final void setOnDropAttribute(boolean inOnDropAttribute)
  {
    mOnDropAttribute = inOnDropAttribute;
    if (mOnDropAttribute)
    {
      exportOnDrop();
    }
  }

  private void reorderAndAdd()
  {
    mDelegate.removeAll();
    int pos = mDescending ? mItems.size() - 1 : 0;
    for (DndItemWrapper<I> value : mItems)
    {
      value.setPos(pos);
      pos = mDescending ? pos - 1 : pos + 1;
      mDelegate.add(value.getItem());
    }
  }

  /* attribute calls */
  private void onMouseMove(int inPos)
  {
    mDelegate.onMouseMove(inPos, mItems.get(inPos).getItem());
  }

  private void onDragStart(int inPos)
  {
    mDraggingPos = inPos;
  }

  /* attribute calls */
  private void onDrop(int inPos)
  {
    onDrop(mDraggingPos, inPos);
    mDelegate.onDrop(inPos, mItems.get(inPos).getItem());
  }

  private void onDrop(int inDrag, int inDrop)
  {
    I dropped = mItems.get(inDrop).getItem();

    DndItemWrapper<I> item = mItems.get(mDescending ? inDrop : inDrag);
    mItems.remove(item);
    mItems.add(mDescending ? inDrag : inDrop, item);
    reorderAndAdd();

    if (dropped.asWidget() != null)
    {
      dropped.asWidget().removeStyleName(sOnDragOverStyle);
    }
  }

  private native void exportOnMouseMove()
  /*-{
        var that = this;
        $wnd.onMouseMove = $entry(function(inEvent, inPos)
        {
          try
          {
            if (ev.which == 1)
            {
              var target = inEvent.target;
              target.dragDrop();
            }
          }
          catch(err) {}
          that.@h.style.g.client.ui.DndController::onMouseMove(I)(inPos);
        });
  }-*/;

  private native void exportOnDrop()
  /*-{
        var that = this;
        $wnd.onDrop = $entry(function(inEvent, inPos)
        {
          that.@h.style.g.client.ui.DndController::onDrop(I)(inPos);
        });
  }-*/;

  private void addAttribute(I inItem, int inPos)
  {
    if (mOnMouseMoveAttribute)
    {
      inItem.asWidget().getElement().setAttribute("onmousemove",
          "onMouseMove(event, '" + inPos + "')");
    }
    if (mOnDropAttribute)
    {
      inItem.asWidget().getElement().setAttribute("ondrop", "onDrop(event, '" + inPos + "')");
    }
  }

  private void fire(DragStartEvent inEvent)
  {
    for (EventHandler value : mHandlers.get(DragStartEvent.getType()))
    {
      ((DragStartHandler) value).onDragStart(inEvent);
    }
  }

  private void fire(MouseMoveEvent inEvent)
  {
    for (EventHandler value : mHandlers.get(MouseMoveEvent.getType()))
    {
      ((MouseMoveHandler) value).onMouseMove(inEvent);
    }
  }

  private void fire(DropEvent inEvent)
  {
    for (EventHandler value : mHandlers.get(DropEvent.getType()))
    {
      ((DropHandler) value).onDrop(inEvent);
    }
  }

  private void fire(DragOverEvent inEvent)
  {
    for (EventHandler value : mHandlers.get(DragOverEvent.getType()))
    {
      ((DragOverHandler) value).onDragOver(inEvent);
    }
  }

  private void fire(DragLeaveEvent inEvent)
  {
    for (EventHandler value : mHandlers.get(DragLeaveEvent.getType()))
    {
      ((DragLeaveHandler) value).onDragLeave(inEvent);
    }
  }

  protected DndItemWrapper<I> create(int inPos, I inItem)
  {
    return new Diw(inPos, inItem);
  }

  public interface DndItemWrapper<I extends IsWidget> extends DragStartHandler, DragOverHandler,
  DragLeaveHandler, DropHandler, MouseMoveHandler, HasPosition
  {
    I getItem();
  }

  private class Diw implements DndItemWrapper<I>
  {
    private int mPos;
    private I mItem;

    public Diw(int inPos, I inItem)
    {
      mPos = inPos;
      mItem = inItem;

      Widget w = mItem.asWidget();
      if (mWatch.size() != 0)
      {
        w.getElement().setDraggable(Element.DRAGGABLE_TRUE);
      }
      if (mWatch.contains(DragStartEvent.getType()))
      {
        w.addBitlessDomHandler(this, DragStartEvent.getType());
      }
      if (mWatch.contains(MouseMoveEvent.getType()))
      {
        w.addDomHandler(this, MouseMoveEvent.getType());
      }
      if (mWatch.contains(DragOverEvent.getType()))
      {
        w.addDomHandler(this, DragOverEvent.getType());
      }
      if (mWatch.contains(DragLeaveEvent.getType()))
      {
        w.addDomHandler(this, DragLeaveEvent.getType());
      }
      if (mWatch.contains(DropEvent.getType()))
      {
        w.addDomHandler(this, DropEvent.getType());
      }
    }

    @Override
    public I getItem()
    {
      return mItem;
    }

    @Override
    public void setPos(int inPos)
    {
      addAttribute(mItem, inPos);
      if (mItem instanceof HasPosition)
      {
        ((HasPosition) mItem).setPos(inPos);
      }
      mPos = inPos;
    }

    @Override
    public int getPos()
    {
      return mPos;
    }

    @Override
    public void onMouseMove(MouseMoveEvent inEvent)
    {
      mDelegate.onMouseMove(mPos, mItem);

      fire(inEvent);
    }

    @Override
    public void onDragStart(DragStartEvent inEvent)
    {
      DndController.this.onDragStart(mPos);

      if (inEvent != null)
      {
        // Must set for FireFox
        // inEvent.setData("text", "hi there");
        inEvent.getDataTransfer().setData("text", "" + mPos);

        // 10,10 indicates the pointer offset, not the image size.
        inEvent.getDataTransfer().setDragImage(mItem.asWidget().getElement(), 10, 10);
      }

      mDelegate.onDragStart(mPos, mItems.get(mPos).getItem());

      fire(inEvent);
    }

    @Override
    public void onDragOver(DragOverEvent inEvent)
    {
      if (inEvent != null)
      {
        inEvent.preventDefault();
        mItem.asWidget().addStyleName(sOnDragOverStyle);
      }

      mDelegate.onDragOver(mPos, mItem);

      fire(inEvent);
    }

    @Override
    public void onDragLeave(DragLeaveEvent inEvent)
    {
      if (inEvent != null)
      {
        mItem.asWidget().removeStyleName(sOnDragOverStyle);
      }

      mDelegate.onDragLeave(mPos, mItem);

      fire(inEvent);
    }

    @Override
    public void onDrop(DropEvent inEvent)
    {
      DndController.this.onDrop(mDraggingPos, mPos);

      if (inEvent != null)
      {
        inEvent.preventDefault();
        mItem.asWidget().removeStyleName(sOnDragOverStyle);
      }

      mDelegate.onDrop(mPos, mItems.get(mPos).getItem());

      fire(inEvent);
    }
  }

  private static native void consoleNative(String inMessage)
  /*-{
       console.log(inMessage);
  }-*/;
}