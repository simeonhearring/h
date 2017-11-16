package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PersonInfoPresenter;
import h.model.shared.khall.Person;

public class PersonInfoView extends AbstractView<PersonInfoPresenter>
  implements PersonInfoPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonInfoView>
  {
  }

  @UiField
  Heading mName;

  @UiField
  Span mRole, mAddressLine, mCityLine, mHome, mMobile, mEmail;

  public PersonInfoView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PersonInfoPresenter(this).handlers();
  }

  @Override
  public void setPerson(Person inPerson)
  {
    mName.setText(inPerson.getName());
    mRole.setText(text(inPerson.getRole()));
    mAddressLine.setText(inPerson.getAddressLine());
    mCityLine.setText(inPerson.getCityLine());
    mHome.setText(inPerson.getHome());
    mMobile.setText(inPerson.getMobile());
    mEmail.setText(inPerson.getEmail());
  }
}