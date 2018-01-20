package h.khall.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.Tooltip;
import org.gwtbootstrap3.client.ui.constants.IconPosition;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Placement;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import h.khall.client.ui.event.PersonInfoEvent;
import h.model.shared.khall.Person;
import h.style.g.client.ui.SpanIconView;

public class ContactView extends SpanIconView implements ClickHandler
{
  private static IconType[] sOptions =
  {
      IconType.MOBILE_PHONE, IconType.ENVELOPE, IconType.PHONE, IconType.ADDRESS_BOOK
  };

  private long mId;
  private int mIndex = 0;
  private Map<IconType, String> mValues;

  private Anchor mName;
  private Span mAddress;
  private Tooltip mAddressTip, mStudentTip, mPublisherTip;

  private Icon mStudent;
  private Icon mPublisher;

  public ContactView(long inId)
  {
    mId = inId;
    mName = name();
    mAddress = new Span();
    mAddressTip = new Tooltip(mAddress, "");
    mAddressTip.setPlacement(Placement.BOTTOM);
    mStudent = new Icon(IconType.GRADUATION_CAP);
    mPublisher = new Icon(IconType.BRIEFCASE);
    mStudentTip = new Tooltip(mStudent, "");
    mPublisherTip = new Tooltip(mPublisher, "");
    mStudentTip.setPlacement(Placement.BOTTOM);
    mPublisherTip.setPlacement(Placement.BOTTOM);

    setMarginRight(5.0);
    setIcon(IconType.MOBILE_PHONE);
    register(addClickHandler(this));
    register(mName.addClickHandler(this));

    mValues = new HashMap<>();
  }

  public void person(Person inPerson)
  {
    setText(inPerson.getMobile());
    mValues.put(IconType.MOBILE_PHONE, inPerson.getMobile());
    mValues.put(IconType.ENVELOPE, inPerson.getEmail());
    mValues.put(IconType.PHONE, inPerson.getHome());
    mValues.put(IconType.ADDRESS_BOOK, inPerson.gAddress());

    mName.setText(inPerson.getName());
    mName.setIcon(inPerson.isMale() ? IconType.MALE : IconType.FEMALE);
    if (!inPerson.isMember())
    {
      mName.getElement().getStyle().setTextDecoration(TextDecoration.LINE_THROUGH);
    }
    else
    {
      mName.getElement().getStyle().clearTextDecoration();
    }

    mAddress.setText(inPerson.gAddress1());
    mAddressTip.setTitle(inPerson.gAddress());

    setColor(mStudent, mStudentTip, inPerson.isStudent(), "student");
    setColor(mPublisher, mPublisherTip, inPerson.isPublisher(), "publisher");
  }

  private void setColor(Icon inIcon, Tooltip inTip, boolean inB, String inText)
  {
    inIcon.setColor(inB ? "#1ab394" : "#f8ac59");
    inTip.setTitle((inB ? "Is " : "Is not ") + inText);
  }

  @Override
  public void onClick(ClickEvent inEvent)
  {
    Object source = inEvent.getSource();

    if (isSource(inEvent))
    {
      IconType type = sOptions[next()];
      setIcon(type);
      setText(mValues.get(type));
    }
    else if (mName.equals(source))
    {
      fire(new PersonInfoEvent(mId));
    }
  }

  public SpanIconView getContact()
  {
    return this;
  }

  public Icon getStudent()
  {
    return mStudent;
  }

  public Icon getPublisher()
  {
    return mPublisher;
  }

  public Anchor getName()
  {
    return mName;
  }

  public Span getAddress()
  {
    return mAddress;
  }

  private int next()
  {
    mIndex = mIndex == sOptions.length - 1 ? 0 : mIndex + 1;
    return mIndex;
  }

  public long getId()
  {
    return mId;
  }

  private Anchor name()
  {
    Anchor ret = new Anchor();
    ret.addStyleName("client-link");
    ret.setIconPosition(IconPosition.RIGHT);
    return ret;
  }
}