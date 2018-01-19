package h.khall.client.model;

import java.util.List;

import h.model.shared.khall.Categories;
import h.model.shared.khall.Categories.Category;
import h.model.shared.khall.Person;

public class CategoryPresenter extends HasGLabelPresenter<Category>
{
  public CategoryPresenter(Display inDisplay)
  {
    super(inDisplay);
  }

  @Override
  String name()
  {
    return "Categories";
  }

  @Override
  Category[] values()
  {
    return Categories.Category.values();
  }

  @Override
  List<Category> values(Person inPerson)
  {
    return inPerson.getCategories().getCategories();
  }

  // @Override
  // public CategoryPresenter handlers()
  // {
  // register(addHandler(ParticipantInfoEvent.TYPE, this));
  // return this;
  // }
  //
  // @Override
  // public void onClick(ClickEvent inEvent)
  // {
  // if (mId != null)
  // {
  // String[] option = mDisplay.gOption(inEvent);
  // Person person = mClient.gPerson(mId);
  // update(Boolean.valueOf(option[1]), values(person),
  // EnumUtil.valueOf(option[0], values()));
  // fire(new PersonSaveCommand(person));
  // }
  // else
  // {
  // mDisplay.undo(inEvent);
  // mDisplay.notify("Select someone first.");
  // }
  // }
  //
  // @Override
  // public void dispatch(ParticipantInfoEvent inEvent)
  // {
  // mId = inEvent.getParticipantId();
  // mDisplay.setOptions(values(mClient.gPerson(mId)));
  // }

  public interface Display extends HasGLabelPresenter.Display
  {
  }
}