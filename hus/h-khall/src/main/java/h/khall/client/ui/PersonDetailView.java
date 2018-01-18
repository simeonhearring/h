package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PersonDetailPresenter;
import h.model.shared.khall.Person;

public class PersonDetailView extends AbstractView<PersonDetailPresenter>
  implements PersonDetailPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonDetailView>
  {
  }

  @UiField
  Heading mName;

  @UiField
  Span mRole, mAddressLine, mCityLine, mHome, mMobile, mEmail;

  public PersonDetailView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PersonDetailPresenter(this).handlers();
  }

  @Override
  public void setPerson(Person inPerson)
  {
    mName.setText(inPerson.getName());
    mRole.setText(text(inPerson.getRole()));
    mAddressLine.setText(inPerson.gAddressLine());
    mCityLine.setText(inPerson.gCityLine());
    mHome.setText(inPerson.getHome());
    mMobile.setText(inPerson.getMobile());
    mEmail.setText(inPerson.getEmail());
  }
}