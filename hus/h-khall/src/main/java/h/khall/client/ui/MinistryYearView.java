package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Div;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.MinistryYearPresenter;
import h.model.shared.util.TimeUtil;
import h.style.g.client.ui.InputView;
import h.style.g.client.ui.TableView;

public class MinistryYearView extends AbstractView<MinistryYearPresenter>
  implements MinistryYearPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, MinistryYearView>
  {
  }

  @UiField
  Label mName;

  @UiField
  TableView mTable;

  @UiField
  Button mSave;

  public MinistryYearView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new MinistryYearPresenter(this).handlers();

    int year = TimeUtil.currentYear() - 2000;

    int row = mTable.getRowCount();
    mTable.setWidget(row, 0, new Label("Month"));
    mTable.setWidget(row, 1, new Label("Place"));
    mTable.setWidget(row, 2, new Label("Video"));
    mTable.setWidget(row, 3, new Label("Hours"));
    mTable.setWidget(row, 4, new Label("RV"));
    mTable.setWidget(row, 5, new Label("Studies"));
    mTable.setWidget(row, 6, new Label("Comments"));
    mTable.setWidget(row, 7, new Label("More"));

    String[][] months =
    {
        {"Sep", "9", "0"}, {"Oct", "10", "0"}, {"Nov", "11", "0"}, {"Dec", "12", "0"}, {"Jan", "1", "1"}, {"Feb", "2", "1"}, {"Mar", "3", "1"}, {"Apr", "4", "1"}, {"May", "5", "1"}, {"Jun", "6", "1"}, {"Jul", "7", "1"}, {"Aug", "8", "1"}
    };

    for (String[] value : months)
    {
      MinistryMonthView mm = monthView(year, value[0], value[1], value[2]);

      row = mTable.getRowCount();

      mTable.setWidget(row, 0, mm.getLabel());
      mTable.setWidget(row, 1, mm.getPlacement());
      mTable.setWidget(row, 2, mm.getVideo());
      mTable.setWidget(row, 3, mm.getHours());
      mTable.setWidget(row, 4, mm.getRv());
      mTable.setWidget(row, 5, mm.getStudy());
      mTable.setWidget(row, 6, mm.getComment());
      mTable.setWidget(row, 7, more(mm));
    }
  }

  @Override
  public HasText getName()
  {
    return mName;
  }

  private MinistryMonthView monthView(int inYear, String inMoName, String inMoNum, String inYearMod)
  {
    MinistryMonthView ret = new MinistryMonthView();

    mPresenter.put(Integer.parseInt(inMoNum), ret);

    int yearmod = inYear + Integer.parseInt(inYearMod);

    ret.getMonth().setText(inMoName + " " + yearmod);
    ret.addChangeHandler();

    ((InputView) ret.getComment()).getInput().getElement().getStyle().setWidth(150.0, Unit.PX);

    return ret;
  }

  private IsWidget more(MinistryMonthView inView)
  {
    Div ret = new Div();

    final FlexTable table = new FlexTable();
    table.setVisible(false);

    table.setWidget(0, 0, new Label("Send"));
    table.setWidget(0, 1, inView.mSend);

    table.setWidget(1, 0, new Label("Other"));
    table.setWidget(1, 1, inView.mOther);

    table.setWidget(2, 0, new Label("Partial"));
    table.setWidget(2, 1, inView.mPartial);

    table.setWidget(3, 0, new Label("Pioneer"));
    table.setWidget(3, 1, inView.mPioneer);

    Icon icon = new Icon(IconType.ELLIPSIS_H);
    icon.addClickHandler(new ClickHandler()
    {
      @Override
      public void onClick(ClickEvent inEvent)
      {
        table.setVisible(!table.isVisible());
      }
    });

    ret.add(icon);
    ret.add(table);

    return ret;
  }

  @UiHandler(
  {
      "mSave"
  })
  public void onClick(ClickEvent inEvent)
  {
    mPresenter.save();
  }

  public void changePub(Long inPubId, int[] inYearMonth)
  {
    mPresenter.changePub(inPubId, inYearMonth);
  }

  public void changeMonth(int[] inYearMonth)
  {
    mPresenter.changeMonth(inYearMonth);
  }
}