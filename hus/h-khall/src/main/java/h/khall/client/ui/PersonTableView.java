package h.khall.client.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PersonTablePresenter;
import h.model.shared.khall.Person;

public class PersonTableView extends AbstractView<PersonTablePresenter>
  implements PersonTablePresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonTableView>
  {
  }

  @UiField
  FlexTable mTable;

  Map<Long, ContactView> mMap;

  public PersonTableView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PersonTablePresenter(this).handlers();
    mMap = new HashMap<>();
  }

  private void add(Person inPerson)
  {
    int row = mTable.getRowCount();
    int column = -1;

    ContactView cv = new ContactView(inPerson.getIdLong());

    cv.person(inPerson);

    mMap.put(inPerson.getIdLong(), cv);

    mTable.setWidget(row, ++column, cv.getName());

    mTable.setWidget(row, ++column, cv.getContact());

    mTable.setWidget(row, ++column, cv.getAddress());

    // mTable.getCellFormatter().getElement(row,
    // column).addClassName("client-status");

    mTable.setWidget(row, ++column, cv.getStudent());
    mTable.setWidget(row, ++column, cv.getPublisher());
  }

  @Override
  public void update(Person inPerson)
  {
    long id = inPerson.getIdLong();
    if (mMap.containsKey(id))
    {
      mMap.get(id).person(inPerson);
    }
  }

  public void clear()
  {
    mTable.removeAllRows();
    mMap.clear();
  }

  public void setValues(List<Person> inValues)
  {
    for (Person value : inValues)
    {
      add(value);
    }
  }
}