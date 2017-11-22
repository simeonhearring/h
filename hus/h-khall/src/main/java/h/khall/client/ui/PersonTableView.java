package h.khall.client.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Tooltip;
import org.gwtbootstrap3.client.ui.constants.IconPosition;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.ui.event.PersonInfoEvent;
import h.model.shared.khall.Person;
import h.style.g.client.ui.AbstractView;
import h.style.g.client.ui.SpanIconView;

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

  private void add(final Person inPerson)
  {
    int row = mTable.getRowCount();
    int column = -1;

    mTable.setWidget(row, ++column, name(inPerson));

    final Map<IconType, String> values = new HashMap<>();
    values.put(IconType.MOBILE_PHONE, inPerson.getMobile());
    values.put(IconType.ENVELOPE, inPerson.getEmail());
    values.put(IconType.PHONE, inPerson.getHome());
    values.put(IconType.ADDRESS_BOOK, inPerson.gAddress());

    final SpanIconView contact = new SpanIconView();
    contact.setMarginRight(5.0);

    contact.setIcon(IconType.MOBILE_PHONE);
    contact.setText(inPerson.getMobile());
    mTable.setWidget(row, ++column, contact);

    ClickHandler clickHandler = new ClickHandler()
    {
      private int mIndex = 0;
      private IconType[] mOptions =
      {
          IconType.MOBILE_PHONE, IconType.ENVELOPE, IconType.PHONE, IconType.ADDRESS_BOOK
      };

      @Override
      public void onClick(ClickEvent inEvent)
      {
        IconType type = mOptions[next()];
        contact.setIcon(type);
        contact.setText(values.get(type));
      }

      private int next()
      {
        mIndex = mIndex == mOptions.length - 1 ? 0 : mIndex + 1;
        return mIndex;
      }
    };
    contact.addClickHandler(clickHandler);

    Span address = new Span();
    new Tooltip(address, inPerson.gAddress());
    address.setText(inPerson.gAddress1());
    mTable.setWidget(row, ++column, address);

    Span status = new Span();
    boolean student = inPerson.isStudent();
    status.setStyleName("label label-" + (student ? "primary" : "warning"));
    status.setText(getStudentStatus(student));
    mTable.getCellFormatter().getElement(row, column).addClassName("client-status");
    mTable.setWidget(row, ++column, status);
  }

  private Anchor name(final Person inPerson)
  {
    Anchor ret = new Anchor();
    ret.addStyleName("client-link");
    ret.setText(inPerson.getName());

    ret.setIcon(inPerson.isMale() ? IconType.MALE : IconType.FEMALE);
    ret.setIconPosition(IconPosition.RIGHT);

    register(ret.addClickHandler(new ClickHandler()
    {
      @Override
      public void onClick(ClickEvent inEvent)
      {
        fire(new PersonInfoEvent(inPerson));
      }
    }));
    return ret;
  }

  private String getStudentStatus(boolean inStatus)
  {
    return inStatus ? "Enrolled" : "Not Enrolled";
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