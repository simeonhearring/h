package h.khall.client.ui;

import java.util.List;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Popover;
import org.gwtbootstrap3.client.ui.constants.BadgePosition;
import org.gwtbootstrap3.client.ui.constants.Placement;
import org.gwtbootstrap3.client.ui.constants.Trigger;
import org.gwtbootstrap3.client.ui.html.UnorderedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.PartInfoPresenter;
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

  public PartInfoView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new PartInfoPresenter(this).handlers();
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