package h.khall.client.ui;

import java.util.List;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Popover;
import org.gwtbootstrap3.client.ui.constants.BadgePosition;
import org.gwtbootstrap3.client.ui.constants.IconPosition;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Placement;
import org.gwtbootstrap3.client.ui.constants.Trigger;
import org.gwtbootstrap3.client.ui.html.UnorderedList;
import org.gwtbootstrap3.extras.toggleswitch.client.ui.ToggleSwitchRadio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PartInfoPresenter;
import h.khall.client.ui.event.PartInfoEvent;
import h.khall.client.ui.event.PersonInfoEvent;
import h.model.shared.Person.Gender;
import h.model.shared.khall.PartInfo.Info;

public class PartInfoView extends AbstractView<PartInfoPresenter>
  implements PartInfoPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, PartInfoView>
  {
  }

  @UiField
  UnorderedList mList;

  @UiField
  Heading mName;

  @UiField
  ToggleSwitchRadio mMale, mFemale;

  @UiField
  Button mRecommend;

  public PartInfoView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PartInfoPresenter(this).handlers();
  }

  @UiHandler(
  {
      "mMale", "mFemale"
  })
  public void onValueChange(ValueChangeEvent<Boolean> inEvent)
  {
    Gender gender = mMale.getValue() ? Gender.Male : mFemale.getValue() ? Gender.Female : null;
    fire(new PartInfoEvent(gender));
  }

  @UiHandler(
  {
      "mRecommend"
  })
  public void onClick(ClickEvent inEvent)
  {
    mPresenter.recommend();
  }

  @Override
  public void setPartName(String inName)
  {
    mName.setText(inName);
  }

  @Override
  public void clear()
  {
    mList.clear();
  }

  @Override
  public void add(Info inInfo, boolean inFirst, List<String> inContent)
  {
    AnchorListItem item = new AnchorListItem();
    item.setText(inInfo.getPerson().getName());
    item.setStyleName("list-group-item kh_list" + (inFirst ? " fist-item" : ""));
    item.setBadgePosition(BadgePosition.RIGHT);
    item.setBadgeText(format("MM/yy", inInfo.getLast()));
    item.setIcon(inInfo.getPerson().isMale() ? IconType.MALE : IconType.FEMALE);
    item.setIconPosition(IconPosition.RIGHT);

    final long id = inInfo.getPerson().getIdLong();
    item.addClickHandler(new ClickHandler()
    {
      @Override
      public void onClick(ClickEvent inEvent)
      {
        fire(new PersonInfoEvent(id));
      }
    });

    Popover pop = new Popover(item);
    pop.setIsHtml(true);
    pop.setContainer("body");
    pop.setTitle(inInfo.getPerson().gNameAge());
    pop.setTrigger(Trigger.HOVER);
    pop.setPlacement(Placement.LEFT);
    pop.setContent(popContent(inContent));

    mList.add(item);
  }

  private static String popContent(List<String> inContent)
  {
    StringBuilder html = new StringBuilder("<small>");
    html.append("<ul style='padding-left: 15px;'>");
    for (String value : inContent)
    {
      html.append("<li style='width: 225px'>").append(value).append("</li>");
    }
    html.append("</ul></small>");
    return html.toString();
  }
}