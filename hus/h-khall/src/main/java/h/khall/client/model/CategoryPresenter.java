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

  public interface Display extends HasGLabelPresenter.Display
  {
  }
}