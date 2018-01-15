package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import h.model.shared.util.StringUtil;

@SuppressWarnings("serial")
public class Categories implements Serializable
{
  public enum Category
  {
    RESTRICTIONS,
    SHUT_IN,
    SPECIAL_NEED,
    SICKLY,
    DEAF,
    BLIND,
    FIFTEEN_MINUTE_INCREMENT,
    INFIRM_REGULAR_PIONEER;

    public String getLabel()
    {
      return StringUtil.toTitle(name().replaceAll("_", " "));
    }
  }

  private List<Category> mCategories;

  public void normalize()
  {
    if (mCategories == null)
    {
      mCategories = new ArrayList<>();
    }
  }

  public List<Category> getCategories()
  {
    return mCategories;
  }

  public void setCategories(List<Category> inCategories)
  {
    mCategories = inCategories;
  }

  public boolean contains(Category inCategory)
  {
    return mCategories.contains(inCategory);
  }
}