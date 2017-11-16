package h.khall.client.ui;

import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PersonsPresenter;
import h.model.shared.khall.Person;

public class PersonsView extends AbstractView<PersonsPresenter> implements PersonsPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonsView>
  {
  }

  @UiField
  Input mSearch;

  @UiField
  Button mGo;

  @UiField
  Span mCount;

  @UiField
  PersonTableView mAll, mElders, mRegular;

  public PersonsView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PersonsPresenter(this).handlers();
  }

  @UiHandler(
  {
      "mGo"
  })
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();
    if (mGo.equals(source))
    {
      mPresenter.search(mSearch.getText());
    }
  }

  @Override
  public void setAll(List<Person> inList)
  {
    mCount.setText(inList.size() + " shown");
    mAll.clear();
    mAll.setValues(inList);
  }

  @Override
  public void setElders(List<Person> inList)
  {
    mCount.setText(inList.size() + " shown");
    mElders.clear();
    mElders.setValues(inList);
  }

  @Override
  public void setRegular(List<Person> inList)
  {
    mCount.setText(inList.size() + " shown");
    mRegular.clear();
    mRegular.setValues(inList);
  }
}