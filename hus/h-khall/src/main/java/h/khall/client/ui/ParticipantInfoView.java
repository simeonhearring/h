package h.khall.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.ParticipantInfoPresenter;
import h.model.shared.khall.Categories;
import h.model.shared.khall.Categories.Category;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Part;
import h.model.shared.khall.Roles;
import h.model.shared.khall.Roles.Role;

public class ParticipantInfoView extends AbstractView<ParticipantInfoPresenter>
  implements ParticipantInfoPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, ParticipantInfoView>
  {
  }

  @UiField
  Label mName;

  @UiField
  HTMLPanel mParts, mHalls, mRoles, mCategories;

  public ParticipantInfoView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new ParticipantInfoPresenter(this).handlers();

    checkboxes();
  }

  private void checkboxes()
  {
    for (final Part value : Part.assignable())
    {
      final CheckBox check = new CheckBox(value.getLabel(true));
      check.setId(value.name());
      check.addClickHandler(new ClickHandler()
      {
        @Override
        public void onClick(ClickEvent inEvent)
        {
          mPresenter.check(value, check.getValue());
        }
      });
      mParts.add(check);
    }

    for (final Hall value : Hall.values())
    {
      final CheckBox check = new CheckBox(value.getLabel());
      check.setId(value.name());
      check.addClickHandler(new ClickHandler()
      {
        @Override
        public void onClick(ClickEvent inEvent)
        {
          mPresenter.check(value, check.getValue());
        }
      });
      mHalls.add(check);
    }

    for (final Role value : Roles.Role.values())
    {
      final CheckBox check = new CheckBox(value.getLabel());
      check.setId(value.name());
      check.addClickHandler(new ClickHandler()
      {
        @Override
        public void onClick(ClickEvent inEvent)
        {
          mPresenter.check(value, check.getValue());
        }
      });
      mRoles.add(check);
    }

    for (final Category value : Categories.Category.values())
    {
      final CheckBox check = new CheckBox(value.getLabel());
      check.setId(value.name());
      check.addClickHandler(new ClickHandler()
      {
        @Override
        public void onClick(ClickEvent inEvent)
        {
          mPresenter.check(value, check.getValue());
        }
      });
      mCategories.add(check);
    }

  }

  @Override
  public void setName(String inName)
  {
    mName.setText(inName);
  }

  @Override
  public void check(List<Part> inParts, List<Hall> inHalls, List<Role> inRoles,
      List<Category> inCategories)
  {
    check(keys(inParts), mParts);
    check(keys(inHalls), mHalls);
    check(keys(inCategories), mCategories);
    check(keys(inRoles), mRoles);
  }

  private static <T extends Enum<?>> List<String> keys(List<T> inEnums)
  {
    List<String> ret = new ArrayList<>();
    for (Enum<?> value : inEnums)
    {
      ret.add(value.name());
    }
    return ret;
  }

  private static void check(List<String> inKeys, HTMLPanel inPanel)
  {
    for (int i = 0; i < inPanel.getWidgetCount(); i++)
    {
      CheckBox check = (CheckBox) inPanel.getWidget(i);
      check.setValue(inKeys.contains(check.getId()));
    }
  }
}