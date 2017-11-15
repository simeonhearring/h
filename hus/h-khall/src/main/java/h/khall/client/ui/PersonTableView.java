package h.khall.client.ui;

import java.util.List;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import h.khall.shared.model.Person;
import h.style.g.client.ui.AbstractView;

public class PersonTableView extends AbstractView
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonTableView>
  {
  }

  @UiField
  FlexTable mTable;

  public PersonTableView()
  {
    initWidget(BINDER.createAndBindUi(this));
  }

//  <tr>
//  <td class="client-avatar">
//      <img alt="image" src="img/a2.jpg" />
//  </td>
//  <td>
//      <a data-toggle="tab" href="#contact-1" class="client-link">Anthony Jackson</a>
//  </td>
//  <td> Tellus Institute</td>
//  <td class="contact-type">
//      <i class="fa fa-envelope">
//      </i>
//  </td>
//  <td> gravida@rbisit.com</td>
//  <td class="client-status">
//      <span class="label label-primary">Active</span>
//  </td>
//</tr>

  private void add(Person inPerson)
  {
    int row = mTable.getRowCount();
    int column = -1;

    Anchor name = new Anchor();
    name.addStyleName("client-link");
    name.setText(inPerson.getName());
    mTable.setWidget(row, ++column, name);

    Icon gender = new Icon(inPerson.isMale() ? IconType.MALE : IconType.FEMALE);
    mTable.setWidget(row, ++column, gender);
    mTable.getCellFormatter().getElement(row, column).addClassName("contact-type");

    Span fsg = new Span();
    fsg.setText(inPerson.getFsg());
    mTable.setWidget(row, ++column, fsg);

    Span address = new Span();
    address.setText(inPerson.getAddress1());
    mTable.setWidget(row, ++column, address);

    Span status = new Span();
    status.setStyleName("label label-primary");
    status.setText(inPerson.getStatus(Person.Type.STUDENT));
    mTable.getCellFormatter().getElement(row, column).addClassName("client-status");
    mTable.setWidget(row, ++column, status);
 }

  public void clear()
  {
    mTable.removeAllRows();
  }

  public void setValues(List<Person> inValues)
  {
    for (Person value : inValues)
    {
      add(value);
    }
  }
}