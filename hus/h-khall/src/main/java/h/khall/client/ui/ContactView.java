package h.khall.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Tooltip;
import org.gwtbootstrap3.client.ui.constants.IconPosition;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Span;

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
  private Span mStatus;
  private Tooltip mAddressTip;

  public ContactView(long inId)
  {
    mId = inId;
    mName = name();
    mAddress = new Span();
    mStatus = new Span();
    mAddressTip = new Tooltip(mAddress, "");

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

    mAddress.setText(inPerson.gAddress1());
    mAddressTip.setTitle(inPerson.gAddress());

    boolean student = inPerson.isStudent();
    mStatus.setStyleName("label label-" + (student ? "primary" : "warning"));
    mStatus.setText(student ? "Enrolled" : "Not Enrolled");
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

  public Anchor getName()
  {
    return mName;
  }

  public Span getAddress()
  {
    return mAddress;
  }

  public Span getStatus()
  {
    return mStatus;
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