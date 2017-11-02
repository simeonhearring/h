package h.style.g.mock;

import org.gwtbootstrap3.client.ui.constants.Placement;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;

import h.style.g.client.model.AnchorListItemDisplay;

public class MockAnchorListItemDisplay extends MockIconDisplay implements AnchorListItemDisplay
{
  private String mProperty;
  private Placement mPlacement;
  private boolean mActive;

  public MockAnchorListItemDisplay()
  {
  }

  public MockAnchorListItemDisplay(boolean inVisible)
  {
    setVisible(inVisible);
  }

  public MockAnchorListItemDisplay(String inText, ClickHandler inHandler)
  {
    setText(inText);
    addClickHandler(inHandler);
  }

  public MockAnchorListItemDisplay(String inText, String inTip, String inProperty,
      ClickHandler inHandler)
  {

    setText(inText);
    setTip(inTip);
    mProperty = inProperty;
    addClickHandler(inHandler);
  }

  public MockAnchorListItemDisplay(String inText, String inTip, ClickHandler inHandler)
  {
    setText(inText);
    setTip(inTip);
    addClickHandler(inHandler);
  }

  @Override
  public String getProperty()
  {
    return mProperty;
  }

  @Override
  public void setProperty(String inProperty)
  {
    mProperty = inProperty;
  }

  @Override
  public boolean isProperty(String inProperty)
  {
    return mProperty != null && mProperty.equals(inProperty);
  }

  @Override
  public IsWidget getAnchor()
  {
    return null;
  }

  @Override
  public boolean isSource(ClickEvent inEvent)
  {
    return super.isSource(inEvent);
  }

  @Override
  public void setPlacement(Placement inPlacement)
  {
    mPlacement = inPlacement;
  }

  public Placement getPlacement()
  {
    return mPlacement;
  }

  @Override
  public String getProperty(ClickEvent inEvent)
  {
    return mProperty;
  }

  @Override
  public void setActive(boolean inActive)
  {
    mActive = inActive;
  }

  public boolean isActive()
  {
    return mActive;
  }

  @Override
  public void setTarget(String inTarget)
  {
  }
}