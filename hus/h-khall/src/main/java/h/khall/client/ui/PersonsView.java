package h.khall.client.ui;

import java.util.List;

import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PersonsPresenter;
import h.khall.shared.model.Person;

public class PersonsView extends AbstractView<PersonsPresenter> implements PersonsPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonsView>
  {
  }

  @UiField
  Span mCount;

  @UiField
  PersonTableView mAll;

  public PersonsView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PersonsPresenter(this).handlers();
  }

  @Override
  public void setAll(List<Person> inList)
  {
    mCount.setText(inList.size() + " shown");
    mAll.setValues(inList);
  }
}