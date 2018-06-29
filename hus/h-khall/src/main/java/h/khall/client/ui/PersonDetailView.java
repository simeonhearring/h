package h.khall.client.ui;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.html.Span;
import org.gwtbootstrap3.client.ui.html.UnorderedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PersonDetailPresenter;
import h.khall.client.ui.event.PersonInfoEvent;
import h.model.shared.khall.Person;

public class PersonDetailView extends AbstractView<PersonDetailPresenter>
  implements PersonDetailPresenter.Display, ClickHandler
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PersonDetailView>
  {
  }

  @UiField
  Heading mName;

  @UiField
  UnorderedList mHead, mFamily;

  @UiField
  Label mHeadLabel, mFamilyLabel, mAddressLabel, mFsgLabel, mAgeLabel;

  @UiField
  Span mAddress, mFsg, mAge;

  public PersonDetailView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PersonDetailPresenter(this).handlers();
  }

  @Override
  public void setPerson(Person inPerson)
  {
    mName.setText(inPerson.getName());
    mName.setSubText(text(inPerson.getRole()));

    mAddressLabel.setVisible(true);
    mAddress.setHTML("<br/>" + inPerson.gAddressLine() + "<br/>" + inPerson.gCityLine());

    mAgeLabel.setVisible(true);
    mAge.setHTML(inPerson.gAges());
  }

  @Override
  public void setFsg(String inText)
  {
    mFsgLabel.setVisible(true);
    mFsg.setText(inText);
  }

  @Override
  public void clearHead()
  {
    mHead.clear();
    mHeadLabel.setVisible(false);
  }

  @Override
  public void addHead(Person inPerson)
  {
    if (inPerson != null)
    {
      mHeadLabel.setVisible(true);
      AnchorListItem item = new AnchorListItem(inPerson.getFirst());
      item.setId(inPerson.gId());
      item.addClickHandler(this);
      mHead.add(item);
    }
  }

  @Override
  public void clear()
  {
    mFamily.clear();
    mFamilyLabel.setVisible(false);
  }

  @Override
  public void add(Person inPerson)
  {
    mFamilyLabel.setVisible(true);
    AnchorListItem item = new AnchorListItem(inPerson.getFirst());
    item.setId(inPerson.gId());
    item.addClickHandler(this);
    mFamily.add(item);
  }

  @Override
  public void onClick(ClickEvent inEvent)
  {
    AnchorListItem source = (AnchorListItem) ((Widget) inEvent.getSource()).getParent();
    String id = source.getId();
    Long pubId = Long.valueOf(id);
    fire(new PersonInfoEvent(pubId));
  }
}