package h.style.g.mock;

import org.gwtbootstrap3.client.ui.constants.Placement;

import h.style.g.client.model.AnchorInlineDisplay;

public class MockAnchorInlineDisplay extends MockIconDisplay implements AnchorInlineDisplay
{
  private Placement mPlacement;

  @Override
  public void setPlacement(Placement inPlacement)
  {
    mPlacement = inPlacement;
  }

  public Placement getPlacement()
  {
    return mPlacement;
  }
}