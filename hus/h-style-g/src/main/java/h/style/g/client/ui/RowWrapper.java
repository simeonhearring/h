package h.style.g.client.ui;

import org.gwtbootstrap3.client.ui.Row;

import h.style.g.client.model.RowDisplay;

public class RowWrapper extends AbstractView implements RowDisplay
{
  private Row mRow;

  public RowWrapper(Row inRow)
  {
    mRow = inRow;
  }

  @Override
  public void makeAnimate()
  {
    makeAnimate(mRow);
  }
}